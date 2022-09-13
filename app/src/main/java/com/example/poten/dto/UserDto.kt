package com.example.poten.dto

import com.google.gson.annotations.SerializedName

data class SessionResponse (
        @SerializedName("sessionId")
        var sessionId: String?=null,
        @SerializedName("userResponse")
        var userResponse: UserResponse
)

data class UserResponse(
        var id: Long? = null,
        var email: String? = null,
        var name: String? = null,
)