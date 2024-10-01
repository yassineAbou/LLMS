package org.yassineabou.playground.feature.imageGen.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.yassineabou.playground.feature.imageGen.model.Photo
import org.yassineabou.playground.feature.imageGen.model.randomSizedPhotos

class ImageGenViewModel: ViewModel() {
    private val _listGeneratedPhotos = MutableStateFlow(randomSizedPhotos)
    val listGeneratedPhotos: StateFlow<MutableList<Photo>> = _listGeneratedPhotos


    fun deletePhoto(index: Int) {
        _listGeneratedPhotos.update { list ->
            list.removeAt(index)
            list
        }
    }


}

