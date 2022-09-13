package com.example.poten.dto

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class Test2(
//
//    @SerializedName("pic")
//    val pic: MultipartBody.Part
    val pics: List<MultipartBody.Part>,
    @SerializedName("content")
    val content: String,
    @SerializedName("content2")
    val content2: String
)
