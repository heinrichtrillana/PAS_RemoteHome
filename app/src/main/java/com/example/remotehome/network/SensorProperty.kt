package com.example.remotehome.network

import com.squareup.moshi.Json

data class SensorProperty(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String,
    val type: String,
    val price: Double
)
