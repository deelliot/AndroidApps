package com.delliott.flickrbrowser.data

import com.delliott.flickrbrowser.domain.PhotoModel

class PhotosRepository {
    suspend fun getPhotos(searchTerm: String): List<PhotoModel> {
        return try {
            val searchResponse = ApiServiceProvider.client.fetchImages(
                searchTerm = searchTerm
            )
            val photosList: List<PhotoModel> = searchResponse.photos.photo.map { photo ->
                PhotoModel(
                    owner = photo.owner,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }
            photosList
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getOwner(userId: String): String {
        try {
            val userResponse = ApiServiceProvider.client.fetchOwner(
                userId = userId
            )
            val userName = userResponse.person.realName?.content
            return userName ?: "Unknown"
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return "Unknown"
    }
}
