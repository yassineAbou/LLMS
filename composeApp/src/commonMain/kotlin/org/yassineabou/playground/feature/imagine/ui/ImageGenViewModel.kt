package org.yassineabou.playground.feature.imagine.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.yassineabou.playground.app.core.data.ChutesAiEndPoint.API_KEY
import org.yassineabou.playground.app.core.data.ChutesAiRepository
import org.yassineabou.playground.app.core.data.GenerationState
import org.yassineabou.playground.feature.imagine.model.ImageGenModelList
import org.yassineabou.playground.feature.imagine.model.ImageModel
import org.yassineabou.playground.feature.imagine.model.UrlExample
import kotlin.math.min

class ImageGenViewModel(private val chutesAiRepository: ChutesAiRepository) : ViewModel() {

    // Existing state
    private val _listGeneratedPhotos: MutableStateFlow<MutableList<UrlExample>> = MutableStateFlow(mutableListOf())
    val listGeneratedPhotos: StateFlow<MutableList<UrlExample>> = _listGeneratedPhotos

    private val _tempSelectedImageModel = MutableStateFlow<ImageModel>(ImageGenModelList.newImageModel.first())
    val tempSelectedImageModel: StateFlow<ImageModel> = _tempSelectedImageModel

    private val _selectedImageModel = MutableStateFlow<ImageModel>(ImageGenModelList.newImageModel.first())
    val selectedImageModel: StateFlow<ImageModel> = _selectedImageModel

    private val _imageGenerationState = MutableStateFlow<GenerationState>(GenerationState.Success)
    val imageGenerationState: StateFlow<GenerationState> = _imageGenerationState


    // Pagination for inspiration
    private val fullInspirationList = ImageGenModelList.inspiration
    private var currentPage = 0
    private val pageSize = 5 // Adjust based on performance needs

    private val _loadedInspiration = MutableStateFlow<List<UrlExample>>(emptyList())
    val loadedInspiration: StateFlow<List<UrlExample>> = _loadedInspiration


    // Add a state for the current image index
    private val _currentImageIndex = MutableStateFlow(0)
    val currentImageIndex: StateFlow<Int> = _currentImageIndex


    init {
        loadNextInspirationPage()
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
        viewModelScope.launch {
            _imageGenerationState.value = GenerationState.Loading(id = _currentImageIndex.value)
            val result = chutesAiRepository.generateImage(
                apiKey = API_KEY,
                model = selectedImageModel.value,
                prompt = prompt,
            )

            when {
                result.isSuccess -> {
                    val image = result.getOrNull()
                    _listGeneratedPhotos.update { list ->
                        if (image != null) {
                            list.add(0, image)
                        }
                        list
                    }
                    _imageGenerationState.value = GenerationState.Success
                }
                result.isFailure -> {
                    _imageGenerationState.value = GenerationState.Failure(
                        result.exceptionOrNull()?.message ?: "Image generation failed"
                    )
                }
            }
        }
    }


    // Existing functions
    fun deletePhoto(index: Int) {
        if (_listGeneratedPhotos.value.isNotEmpty()) {
            _listGeneratedPhotos.update { list ->
                val newList = list.toMutableList().apply { removeAt(index) }
                newList
            }
        }
    }

    fun addImage(urlExample: UrlExample) {
        _listGeneratedPhotos.update { list ->
            list.add(0, urlExample)
            list
        }
    }

    fun deleteSelectedPhotos(selectedPhotos: List<UrlExample>) {
        _listGeneratedPhotos.update { list ->
            val newList = list.toMutableList().apply {
                removeAll { it in selectedPhotos }
            }
            newList
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

