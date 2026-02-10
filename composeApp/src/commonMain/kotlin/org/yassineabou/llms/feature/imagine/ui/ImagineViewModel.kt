@file:OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)

package org.yassineabou.llms.feature.imagine.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.util.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.io.IOException
import org.yassineabou.llms.Generated_images
import org.yassineabou.llms.app.core.data.async.AsyncManager
import org.yassineabou.llms.app.core.data.remote.ai.AiRepository
import org.yassineabou.llms.app.core.data.remote.ai.GenerationState
import org.yassineabou.llms.app.core.util.FileKit
import org.yassineabou.llms.app.core.util.ImageMetadataUtil
import org.yassineabou.llms.app.core.util.saveImage
import org.yassineabou.llms.feature.imagine.data.model.ImageModel
import org.yassineabou.llms.feature.imagine.data.model.Inspiration
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

    // Generated Images State
    private val _listGeneratedImages: MutableStateFlow<MutableList<UrlExample>> = MutableStateFlow(mutableListOf())
    val listGeneratedImages: StateFlow<MutableList<UrlExample>> = _listGeneratedImages

    private val _imageGenerationState = MutableStateFlow<GenerationState>(GenerationState.Success)
    val imageGenerationState: StateFlow<GenerationState> = _imageGenerationState

    private var imageGenerationJob: Job? = null

    // Selecting model State
    private val _tempSelectedImageModel = MutableStateFlow<ImageModel>(ImageModel.DEFAULT)
    val tempSelectedImageModel: StateFlow<ImageModel> = _tempSelectedImageModel

    private val _selectedImageModel = MutableStateFlow<ImageModel>(ImageModel.DEFAULT)
    val selectedImageModel: StateFlow<ImageModel> = _selectedImageModel

    // Loading model State

    private val _availableImageModels = MutableStateFlow<List<ImageModel>>(emptyList())
    val availableImageModels: StateFlow<List<ImageModel>> = _availableImageModels.asStateFlow()

    private val _isLoadingModels = MutableStateFlow(false)
    val isLoadingModels: StateFlow<Boolean> = _isLoadingModels.asStateFlow()

    private val _modelsLoadError = MutableStateFlow<String?>(null)
    val modelsLoadError: StateFlow<String?> = _modelsLoadError.asStateFlow()


    // Pagination for inspiration
    private val fullInspirationList = Inspiration.list
    private var currentPage by mutableStateOf(0)
    private val pageSize by mutableStateOf(5)

    private val _loadedInspiration = MutableStateFlow<List<UrlExample>>(emptyList())
    val loadedInspiration: StateFlow<List<UrlExample>> = _loadedInspiration

    private val _currentImageIndex = MutableStateFlow(0)
    val currentImageIndex: StateFlow<Int> = _currentImageIndex

    private val _snackbarMessage = Channel<String>()
    val snackbarMessage = _snackbarMessage.receiveAsFlow()


    init {
        loadNextInspirationPage()

        loadImageModels()

        viewModelScope.launch {
            asyncManager.getAllImages()
                .distinctUntilChanged()
                .collect { images ->
                    val convertedImages = images.mapNotNull { dbImage ->
                        try {
                            val mimeType = ImageMetadataUtil.detectImageMimeType(dbImage.image_data)
                                ?: throw IOException("Invalid image data: Unrecognized format")
                            val base64Image = dbImage.image_data.encodeBase64()
                            val dataUrl = "data:$mimeType;base64,$base64Image"

                            UrlExample(
                                id = dbImage.id,
                                url = dataUrl,
                                prompt = dbImage.prompt
                            )
                        } catch (e: Exception) {
                            null
                        }
                    }

                    _listGeneratedImages.value = convertedImages.toMutableList()
                }
        }
    }

    fun loadImageModels() {
        viewModelScope.launch {
            _isLoadingModels.value = true
            _modelsLoadError.value = null

            aiRepository.getImageModels()
                .onSuccess { models ->
                    _availableImageModels.value = models

                    if (models.isNotEmpty() && _selectedImageModel.value == ImageModel.DEFAULT) {
                        val cheapestModel = models.first()
                        _selectedImageModel.value = cheapestModel
                        _tempSelectedImageModel.value = cheapestModel
                    }
                }
                .onFailure { error ->
                    _modelsLoadError.value = error.message ?: "Failed to load models"

                    if (_availableImageModels.value.isEmpty()) {
                        _availableImageModels.value = listOf(ImageModel.DEFAULT)
                    }
                }

            _isLoadingModels.value = false
        }
    }

    fun loadNextInspirationPage() {
        val start = currentPage * pageSize
        if (start >= fullInspirationList.size) return

        val end = min(start + pageSize, fullInspirationList.size)
        val nextPageItems = fullInspirationList.subList(start, end)

        _loadedInspiration.update { currentList ->
            currentList + nextPageItems
        }
        currentPage++
    }

    fun generateImage(prompt: String) {
        imageGenerationJob = viewModelScope.launch {
            _imageGenerationState.value = GenerationState.Loading(id = _currentImageIndex.value)

            val currentModel = selectedImageModel.value

            val request = PollinationsImageRequest(
                prompt = prompt,
                model = currentModel.modelName,
                width = 1024,
                height = 1024,
                seed = null,
                enhance = false,
                negativePrompt = "worst quality, blurry",
                safe = false,
                quality = if (currentModel.modelName == "gptimage") "medium" else null,
                transparent = false
            )

            val result = aiRepository.generateImage(request)

            when {
                result.isSuccess -> {
                    val image = result.getOrNull()
                    if (image != null) {
                        insertImage(
                            prompt = prompt,
                            imageBytes = image.imageBytes,
                            imageModel = currentModel
                        )
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
            _snackbarMessage.send(result)
        }
    }

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

            asyncManager.insertImage(newImage)
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