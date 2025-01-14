package org.yassineabou.playground.feature.Imagine.ui

import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.yassineabou.playground.feature.Imagine.model.ImageGenModelList
import org.yassineabou.playground.feature.Imagine.model.ImageModel
import org.yassineabou.playground.feature.Imagine.model.UrlExample

class ImageGenViewModel: ViewModel() {

    private val _listGeneratedPhotos: MutableStateFlow<MutableList<UrlExample>> = MutableStateFlow(ImageGenModelList.generated)
    val listGeneratedPhotos: StateFlow<MutableList<UrlExample>> = _listGeneratedPhotos

    private val _tempSelectedImageModel = MutableStateFlow<ImageModel>(ImageGenModelList.realistic.first())
    val tempSelectedImageModel: StateFlow<ImageModel> = _tempSelectedImageModel

    private val _selectedImageModel = MutableStateFlow<ImageModel>(ImageGenModelList.realistic.first())
    val selectedImageModel: StateFlow<ImageModel> = _selectedImageModel

    private val _windowSize = MutableStateFlow(IntSize.Zero)
    val windowSize: StateFlow<IntSize> = _windowSize

    private val _isLargeScreen = MutableStateFlow(false)
    val isLargeScreen: StateFlow<Boolean> = _isLargeScreen


    fun deletePhoto(index: Int) {
        _listGeneratedPhotos.update { list ->
            list.removeAt(index)
            list
        }
    }

    fun addImage(urlExample: UrlExample) {
        _listGeneratedPhotos.update { list ->
            list.add(0, urlExample)
            list
        }
    }

    fun selectTempImageModel(imageModel: ImageModel) {
        _tempSelectedImageModel.value = imageModel
    }

    fun confirmSelectedImageModel() {
        _selectedImageModel.value = _tempSelectedImageModel.value
        _tempSelectedImageModel.value = ImageGenModelList.realistic.first()
    }

    fun setTempSelectedToSelected() {
        _tempSelectedImageModel.value = _selectedImageModel.value
    }

    fun updateWindowSize(newSize: IntSize) {
        _windowSize.value = newSize
        _isLargeScreen.value = calculateIsLargeScreen(newSize)
    }

    private fun calculateIsLargeScreen(size: IntSize): Boolean {
        return size.width >= 840 // Adjust this threshold as needed
    }

}

