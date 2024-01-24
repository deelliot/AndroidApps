package com.delliott.flickrbrowser.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PhotosSearchResponse(
    val photos: PhotosResponse
)

@JsonClass(generateAdapter = true)
data class PhotosResponse(
    val page: Int,
    val photo: List<PhotoResponse>
)

@JsonClass(generateAdapter = true)
data class PhotoResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)