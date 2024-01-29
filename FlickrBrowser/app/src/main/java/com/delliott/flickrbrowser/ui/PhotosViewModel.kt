package com.delliott.flickrbrowser.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delliott.flickrbrowser.data.PhotosRepository
import com.delliott.flickrbrowser.domain.PhotoModel

class PhotosViewModel : ViewModel() {
    private val photosRepository = PhotosRepository()

    private val _photos = MutableLiveData<List<PhotoModel>>()
    val photos: LiveData<List<PhotoModel>>
        get() = _photos
    suspend fun fetchImages(searchTerm: String) {
        if (searchTerm.isBlank()) {
            _photos.value = emptyList()
        }
        _photos.value = photosRepository.getPhotos(searchTerm)
    }
}