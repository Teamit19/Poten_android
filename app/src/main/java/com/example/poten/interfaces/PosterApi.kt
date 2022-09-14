package com.example.poten.interfaces

import com.example.poten.Board.model.PosterResponseList
import retrofit2.Call
import retrofit2.http.GET

interface PosterApi {

    //공고 모두 조회
    @GET("posters/all")
    fun getPosterAll(): Call<PosterResponseList>


}