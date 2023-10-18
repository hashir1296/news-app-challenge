package com.example.newsapp.data

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Keep
@JsonClass(generateAdapter = true)
data class GenericErrorResponseModel(
    @Json(name = "message") val message: String?,
    @Json(name = "status") val status: String?,
    @Json(name = "code") val code: String?
)