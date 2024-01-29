package com.delliott.flickrbrowser.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delliott.flickrbrowser.data.PhotosRepository
import com.delliott.flickrbrowser.domain.PhotoModel
import kotlinx.coroutines.launch

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

    private val _selectedPhoto = MutableLiveData<PhotoModel?>()
    val selectedPhoto: LiveData<PhotoModel?>
        get() = _selectedPhoto

    fun selectPhoto(photoModel: PhotoModel) {
        viewModelScope.launch {
            val ownerName = photosRepository.getOwner(photoModel.owner)
            val selectedPhotoModel = photoModel.copy(ownerName = ownerName)
            _selectedPhoto.value = selectedPhotoModel
        }
    }

    fun clearSelectedPhoto() {
        _selectedPhoto.value = null
    }
}