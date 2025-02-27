package org.yassineabou.playground.feature.imagine.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.yassineabou.playground.feature.imagine.model.EstimatedTimerState
import org.yassineabou.playground.feature.imagine.model.ImageGenModelList
import org.yassineabou.playground.feature.imagine.model.ImageModel
import org.yassineabou.playground.feature.imagine.model.UrlExample
import kotlin.time.Duration.Companion.seconds

class ImageGenViewModel : ViewModel() {

    // Existing state
    private val _listGeneratedPhotos: MutableStateFlow<MutableList<UrlExample>> = MutableStateFlow(mutableListOf())
    val listGeneratedPhotos: StateFlow<MutableList<UrlExample>> = _listGeneratedPhotos

    private val _tempSelectedImageModel = MutableStateFlow<ImageModel>(ImageGenModelList.realistic.first())
    val tempSelectedImageModel: StateFlow<ImageModel> = _tempSelectedImageModel

    private val _selectedImageModel = MutableStateFlow<ImageModel>(ImageGenModelList.realistic.first())
    val selectedImageModel: StateFlow<ImageModel> = _selectedImageModel

    // Timer state
    private val _estimatedTimerState = MutableStateFlow(
        EstimatedTimerState(
            remainingSeconds = 10,
            progress = 0f,
            isTimerCompleted = true
        )
    )
    val estimatedTimerState: StateFlow<EstimatedTimerState> = _estimatedTimerState

    // Add a state for the current image index
    private val _currentImageIndex = MutableStateFlow(0)
    val currentImageIndex: StateFlow<Int> = _currentImageIndex

    private val _isImageGenerated = MutableStateFlow(false)
    val isImageGenerated: StateFlow<Boolean> = _isImageGenerated

    // Coroutine job for the timer
    private var timerJob: Job? = null

    // Function to start the timer
    fun startEstimatedTimer() {
        setIsImageGenerated(false)
        timerJob = viewModelScope.launch {
            for (i in 10 downTo 0) {
                _estimatedTimerState.value = EstimatedTimerState(
                    remainingSeconds = i,
                    progress = 1f - (i / 10f), // Invert the progress calculation
                    isTimerCompleted = false
                )
                delay(1.seconds)
            }

            val randomUrl = ImageGenModelList.inspiration.random().url

            addImage(
                UrlExample(
                    url = randomUrl,
                    description = "We're going to work on generating images next. this is just a prototype with fake data"
                )
            )
            setIsImageGenerated(true)
            _estimatedTimerState.value = _estimatedTimerState.value.copy(isTimerCompleted = true) // Mark timer as completed
        }
    }

    // Function to stop the timer
    fun stopEstimatedTimer() {
        timerJob?.cancel() // Cancel the timer coroutine
        resetEstimatedTimer() // Reset the timer state
    }

    // Reset timer state
    fun resetEstimatedTimer() {
        _estimatedTimerState.value = EstimatedTimerState(
            remainingSeconds = 10,
            progress = 0f,
            isTimerCompleted = true
        )
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

    fun setIsImageGenerated(value: Boolean) {
        _isImageGenerated.value = value
    }

}

