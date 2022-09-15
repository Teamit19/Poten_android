package com.example.poten.interfaces

import com.example.poten.Board.model.PosterResponseList
import com.example.poten.dto.SessionResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PosterApi {

    //공고 모두 조회
    @GET("posters/all")
    fun getPosterAll(): Call<PosterResponseList>

    //동아리 - 공고 모두 조회
    @GET("posters/club/{clubId}")
    fun getPosterByClub(@Path("clubId") clubId:Long): Call<PosterResponseList>


}