package com.example.poten.interfaces

import com.example.poten.dto.Test
import com.example.poten.dto.Test2
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
//    @GET("clubs/test")
//    fun getTest(): Call<Test>
    //@Multipart
    @POST("clubs/test")
    fun sendTest(@Body data: HashMap<String, String>): Call<Test>
    //fun sendTest(@PartMap data: HashMap<String, String>): Call<Test>

    @POST("clubs/test2")
    @Multipart
    fun picTest(
        //@Part pics: List<MultipartBody.Part>,
        @Part pics: MultipartBody.Part,
        @Part("content") content: String,
        @Part("content2") content2: String
    ): Call<Test2>
}