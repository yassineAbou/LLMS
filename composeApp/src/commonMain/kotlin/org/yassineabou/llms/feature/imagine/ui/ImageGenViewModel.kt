@file:OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)

package org.yassineabou.llms.feature.imagine.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.util.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.io.IOException
import org.yassineabou.llms.Generated_images
import org.yassineabou.llms.app.core.data.local.LlmsDatabaseRepository
import org.yassineabou.llms.app.core.data.remote.AiEndPoint.IMAGE_ROUTER_API_KEY
import org.yassineabou.llms.app.core.data.remote.AiRepository
import org.yassineabou.llms.app.core.data.remote.GenerationState
import org.yassineabou.llms.app.core.util.FileKit
import org.yassineabou.llms.app.core.util.ImageMetadataUtil
import org.yassineabou.llms.app.core.util.saveImage
import org.yassineabou.llms.feature.imagine.model.ImageGenModelList
import org.yassineabou.llms.feature.imagine.model.ImageModel
import org.yassineabou.llms.feature.imagine.model.UrlExample
import kotlin.math.min
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ImageGenViewModel(
    private val aiRepository: AiRepository,
    private val llmsDatabaseRepository: LlmsDatabaseRepository
) : ViewModel() {

    // Existing state
    private val _listGeneratedImages: MutableStateFlow<MutableList<UrlExample>> = MutableStateFlow(mutableListOf())
    val listGeneratedImages: StateFlow<MutableList<UrlExample>> = _listGeneratedImages

    private val _tempSelectedImageModel = MutableStateFlow<ImageModel>(ImageGenModelList.newImageModel.first())
    val tempSelectedImageModel: StateFlow<ImageModel> = _tempSelectedImageModel

    private val _selectedImageModel = MutableStateFlow<ImageModel>(ImageGenModelList.newImageModel.first())
    val selectedImageModel: StateFlow<ImageModel> = _selectedImageModel

    private val _imageGenerationState = MutableStateFlow<GenerationState>(GenerationState.Success)
    val imageGenerationState: StateFlow<GenerationState> = _imageGenerationState

    private var imageGenerationJob: Job? = null

    // Pagination for inspiration
    private val fullInspirationList = ImageGenModelList.inspiration
    private var currentPage = 0
    private val pageSize = 5 // Adjust based on performance needs

    private val _loadedInspiration = MutableStateFlow<List<UrlExample>>(emptyList())
    val loadedInspiration: StateFlow<List<UrlExample>> = _loadedInspiration

    // Add a state for the current image index
    private val _currentImageIndex = MutableStateFlow(0)
    val currentImageIndex: StateFlow<Int> = _currentImageIndex

    private val _snackbarMessage = Channel<String>()
    val snackbarMessage = _snackbarMessage.receiveAsFlow()


    init {
        loadNextInspirationPage()
        viewModelScope.launch {
            llmsDatabaseRepository.getAllImages().collect { images -> // images is List<Generated_images>
                // Convert all database images to UrlExample objects
                val convertedImages = images.map { dbImage ->
                    // Detect MIME type and validate image
                    val mimeType = ImageMetadataUtil.detectImageMimeType(dbImage.image_data)
                        ?: throw IOException("Invalid image data: Unrecognized format")
                    // Convert byte array to base64 string
                    val base64Image = dbImage.image_data.encodeBase64()
                    val dataUrl = "data:$mimeType;base64,$base64Image"

                    UrlExample(
                        id = dbImage.id,
                        url = dataUrl,
                        prompt = dbImage.prompt
                    )
                }

                // Update the state flow with the converted list
                _listGeneratedImages.value = convertedImages.toMutableList()
            }
        }
    }

    fun loadNextInspirationPage() {
        val start = currentPage * pageSize
        if (start >= fullInspirationList.size) return

        val end = min(start + pageSize, fullInspirationList.size)
        val nextPageItems = fullInspirationList.subList(start, end)

        // Fix 2: Update using update operator for StateFlow
        _loadedInspiration.update { currentList ->
            currentList + nextPageItems
        }
        currentPage++
    }

    fun generateImage(prompt: String) {
        imageGenerationJob = viewModelScope.launch {
            _imageGenerationState.value = GenerationState.Loading(id = _currentImageIndex.value)
            val result = aiRepository.generateImage(
                apiKey = IMAGE_ROUTER_API_KEY,
                model = selectedImageModel.value,
                prompt = prompt,
            )

            when {
                result.isSuccess -> {
                    val image = result.getOrNull()
                    if (image != null) {
                        insertImage(prompt = prompt, imageBytes = image.imageBytes, imageModel = selectedImageModel.value)
                        _imageGenerationState.value = GenerationState.Success
                    }
                }

                result.isFailure -> {
                    _imageGenerationState.value = GenerationState.Failure(
                        result.exceptionOrNull()?.message ?: "Image generation failed"
                    )
                }
            }
        }
    }

    fun cancelImageGeneration() {
        imageGenerationJob?.takeIf { it.isActive }?.cancel()
        _imageGenerationState.value = GenerationState.Cancelled
    }

    fun resetImageGenerationState() {
        _imageGenerationState.value = GenerationState.Success
    }

    fun downloadImage() {
        viewModelScope.launch {
            val currentIndex = _currentImageIndex.value
            val image = _listGeneratedImages.value.getOrNull(currentIndex) ?: run {
                _snackbarMessage.send("No image selected")
                return@launch
            }

            val (imageData, filename) = runCatching {
                val (mimeType, data) = ImageMetadataUtil.extractImageData(image.url)
                val name = ImageMetadataUtil.generateFileName(image.prompt, mimeType)
                data to name
            }.onFailure { error ->
                _snackbarMessage.send("Invalid image data: ${error.message}")
                return@launch
            }.getOrNull() ?: return@launch

            val result = FileKit.saveImage(bytes = imageData, fileName = filename)
            _snackbarMessage.send(result) // Directly send the String message
        }
    }

    // Existing functions
    fun deleteImage(id: String) {
        viewModelScope.launch {
            if (_listGeneratedImages.value.isNotEmpty()) {
                llmsDatabaseRepository.deleteImageById(id)
            }
        }
    }

    fun insertImage(prompt: String, imageBytes: ByteArray, imageModel: ImageModel) {
        viewModelScope.launch {
            val newImage = Generated_images(
                id = Uuid.random().toString(),
                prompt = prompt,
                image_data = imageBytes,
                image_model_name = imageModel.title,
                generated_at = Clock.System.now().toString()
            )



            viewModelScope.launch {
                llmsDatabaseRepository.insertImage(newImage)
            }
        }
    }

    fun deleteSelectedImages(selectedImages: List<UrlExample>) {
        viewModelScope.launch {
            val idsToDelete = selectedImages.map { it.id }
            if (idsToDelete.isEmpty()) return@launch
            llmsDatabaseRepository.deleteImagesByIds(ids = idsToDelete)
        }
    }

    fun selectTempImageModel(imageModel: ImageModel) {
        _tempSelectedImageModel.value = imageModel
    }

    fun confirmSelectedImageModel() {
        _selectedImageModel.value = _tempSelectedImageModel.value
    }

    fun setTempSelectedToSelected() {
        _tempSelectedImageModel.value = _selectedImageModel.value
    }

    fun updateCurrentImageIndex(index: Int) {
        _currentImageIndex.value = index
    }
}
