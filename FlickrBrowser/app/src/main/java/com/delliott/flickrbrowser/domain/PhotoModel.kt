package com.delliott.flickrbrowser.domain

data class PhotoModel(
    val owner: String,
    val url: String,
    val title: String,
    val ownerName: String = "Unknown",
)