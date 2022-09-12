package com.example.poten.interfaces

import com.example.poten.dto.Test
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("clubs/test")
    fun getTest(): Call<Test>
}