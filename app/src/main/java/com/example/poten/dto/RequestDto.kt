package com.example.poten.dto

import okhttp3.MultipartBody

data class BoardForm (
    var clubId: Long,
    var content: String,
//    var List<MultipartFile> pics;

)

data class SignUpForm(
    var email: String,
    var name: String

)
