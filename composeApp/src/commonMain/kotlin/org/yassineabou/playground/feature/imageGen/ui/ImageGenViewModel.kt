package org.yassineabou.playground.feature.imageGen.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.yassineabou.playground.feature.imageGen.model.ImageGenModelList
import org.yassineabou.playground.feature.imageGen.model.ImageModel
import org.yassineabou.playground.feature.imageGen.model.UrlExample

class ImageGenViewModel: ViewModel() {

    private val _listGeneratedPhotos: MutableStateFlow<MutableList<UrlExample>> = MutableStateFlow(ImageGenModelList.generated)
    val listGeneratedPhotos: StateFlow<MutableList<UrlExample>> = _listGeneratedPhotos

    private val _selectedImageModel = MutableStateFlow<ImageModel>(ImageGenModelList.realistic.first())
    val selectedImageModel: StateFlow<ImageModel> = _selectedImageModel


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

    fun selectImageModel(imageModel: ImageModel) {
        _selectedImageModel.value = imageModel
    }
}

