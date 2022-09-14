package com.example.poten.dto

import com.example.poten.Board.model.UserResponse
import com.google.gson.annotations.SerializedName

data class SessionResponse (
        @SerializedName("sessionId")
        var sessionId: String?=null,
        @SerializedName("userResponse")
        var userResponse: UserResponse
)
