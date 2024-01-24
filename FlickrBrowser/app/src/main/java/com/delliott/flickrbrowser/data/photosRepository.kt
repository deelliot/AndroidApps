package com.delliott.flickrbrowser.data

import com.delliott.flickrbrowser.domain.PhotoModel

class PhotosRepository {
    suspend fun getPhotos(): List<PhotoModel> {

        val searchResponse = ApiServiceProvider.client.fetchImages()
        val photosList: List<PhotoModel> = searchResponse.photos.photo.map { photo ->
            PhotoModel(
                id = photo.id,
                url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                title = photo.title
            )
        }
        return photosList
    }
}