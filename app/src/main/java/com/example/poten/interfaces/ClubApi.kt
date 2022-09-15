package com.example.poten.interfaces

import com.example.poten.Board.model.*
import retrofit2.Call
import retrofit2.http.*

interface ClubApi {

    // 동아리 정보 조회
    @GET("clubs/{clubId}")
    fun getClub(@Path("clubId") clubId: Long): Call<ClubResponse>

    // 동아리 멤버 조회
    @GET("clubs/{clubId}/member")
    fun getClubMembers(@Path("clubId") clubId: Long): Call<List<UserResponse>>

    @POST("clubs/search")
    fun postSearchClub(@Body keyword: HashMap<String, String>): Call<ClubResponseList>



}