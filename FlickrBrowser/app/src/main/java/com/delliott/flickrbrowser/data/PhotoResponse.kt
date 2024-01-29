package com.delliott.flickrbrowser.data

import com.squareup.moshi.Json
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

@JsonClass(generateAdapter = true)
data class OwnerSearchResponse(
    val person: OwnerResponse
)

@JsonClass(generateAdapter = true)
data class OwnerResponse(
    val nsid: String,
    @Json(name = "realname") val realName: StringContent?,
)

@JsonClass(generateAdapter = true)
data class StringContent(
    @Json(name = "_content") val content: String
)