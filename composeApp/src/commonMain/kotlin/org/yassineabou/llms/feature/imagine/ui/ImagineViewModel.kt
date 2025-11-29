@file:OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)

package org.yassineabou.llms.feature.imagine.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import io.ktor.util.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.io.IOException
import org.yassineabou.llms.Generated_images
import org.yassineabou.llms.app.core.data.remote.ai.AiRepository
import org.yassineabou.llms.app.core.data.remote.ai.GenerationState
import org.yassineabou.llms.app.core.data.async.AsyncManager
import org.yassineabou.llms.app.core.util.FileKit
import org.yassineabou.llms.app.core.util.ImageMetadataUtil
import org.yassineabou.llms.app.core.util.saveImage
import org.yassineabou.llms.feature.imagine.data.model.ImageGenModelList
import org.yassineabou.llms.feature.imagine.data.model.ImageModel
import org.yassineabou.llms.feature.imagine.data.model.PollinationsImageRequest
import org.yassineabou.llms.feature.imagine.data.model.UrlExample
import kotlin.math.min
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ImagineViewModel(
    private val aiRepository: AiRepository,
    private val asyncManager: AsyncManager
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
    private var currentPage by mutableStateOf(0)
    private val pageSize by mutableStateOf(5)  // Adjust based on performance needs

    private val _loadedInspiration = MutableStateFlow<List<UrlExample>>(emptyList())
    val loadedInspiration: StateFlow<List<UrlExample>> = _loadedInspiration

    // Add a state for the current image index
    private val _currentImageIndex = MutableStateFlow(0)
    val currentImageIndex: StateFlow<Int> = _currentImageIndex

    private val _snackbarMessage = Channel<String>()
    val snackbarMessage = _snackbarMessage.receiveAsFlow()


    init {
        loadNextInspirationPage()

        // Make sure this collection is actually working
        viewModelScope.launch {
            asyncManager.getAllImages()
                .distinctUntilChanged() // Only emit when data actually changes
                .collect { images ->
                    Logger.d { "Database images updated: ${images.size} images" }

                    // Convert all database images to UrlExample objects
                    val convertedImages = images.mapNotNull { dbImage ->
                        try {
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
                        } catch (e: Exception) {
                            //Logger.e(e) { "Failed to convert database image: ${dbImage.id}" }
                            null // Skip invalid images
                        }
                    }

                    // Update the state flow with the converted list
                    _listGeneratedImages.value = convertedImages.toMutableList()

                    //Logger.d { "UI list updated with ${convertedImages.size} images" }
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

            val request = PollinationsImageRequest(
                prompt = prompt,
                model = selectedImageModel.value.modelName, // e.g., "flux"
                width = 1024,  // You can get these from UI state if needed
                height = 1024,
                seed = 42L, // Example seed
                nologo = true  // Example parameter
            )

            val result = aiRepository.generateImage(request)

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
                asyncManager.deleteImageById(id)
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
                asyncManager.insertImage(newImage)
            }
        }
    }

    fun deleteSelectedImages(selectedImages: List<UrlExample>) {
        viewModelScope.launch {
            val idsToDelete = selectedImages.map { it.id }
            if (idsToDelete.isEmpty()) return@launch
            asyncManager.deleteImagesByIds(ids = idsToDelete)
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