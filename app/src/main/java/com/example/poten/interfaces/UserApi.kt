package com.example.poten.interfaces

import com.example.poten.dto.SessionResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("oauth/kakao/{token}")
    fun getKakao(@Path("token") token : String): Call<SessionResponse>


}