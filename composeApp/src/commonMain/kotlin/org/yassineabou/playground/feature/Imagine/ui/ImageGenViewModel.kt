package org.yassineabou.playground.feature.Imagine.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.yassineabou.playground.feature.Imagine.model.ImageGenModelList
import org.yassineabou.playground.feature.Imagine.model.ImageModel
import org.yassineabou.playground.feature.Imagine.model.UrlExample

class ImageGenViewModel : ViewModel() {

    private val _listGeneratedPhotos: MutableStateFlow<MutableList<UrlExample>> = MutableStateFlow(ImageGenModelList.generated)
    val listGeneratedPhotos: StateFlow<MutableList<UrlExample>> = _listGeneratedPhotos

    private val _tempSelectedImageModel = MutableStateFlow<ImageModel>(ImageGenModelList.realistic.first())
    val tempSelectedImageModel: StateFlow<ImageModel> = _tempSelectedImageModel

    private val _selectedImageModel = MutableStateFlow<ImageModel>(ImageGenModelList.realistic.first())
    val selectedImageModel: StateFlow<ImageModel> = _selectedImageModel

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

    // Function to delete multiple photos by their IDs
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

}

