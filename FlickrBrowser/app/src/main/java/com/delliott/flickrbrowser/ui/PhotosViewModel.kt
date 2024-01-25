package com.delliott.flickrbrowser.ui

import androidx.lifecycle.ViewModel
import com.delliott.flickrbrowser.data.PhotosRepository
import com.delliott.flickrbrowser.domain.PhotoModel

class PhotosViewModel : ViewModel() {
    private val photosRepository = PhotosRepository()
    var photos: List<PhotoModel> = emptyList()

    suspend fun fetchImages(searchTerm: String): List<PhotoModel> {
        if (searchTerm.isBlank()) {
            photos = emptyList()
            return photos
        }
        photos = photosRepository.getPhotos(searchTerm)
        return photos
    }
}