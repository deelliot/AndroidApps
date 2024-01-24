package com.delliott.flickrbrowser.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delliott.flickrbrowser.data.PhotosRepository
import com.delliott.flickrbrowser.domain.PhotoModel
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {

    private val _photos = MutableLiveData<List<PhotoModel>>()
    private val photosRepository = PhotosRepository()

    val photos: LiveData<List<PhotoModel>>
        get() = _photos

    init {
        viewModelScope.launch {
            _photos.value = photosRepository.getPhotos()
        }
    }
}