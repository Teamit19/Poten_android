package com.example.poten.interfaces

import com.example.poten.Board.model.BoardResponse
import com.example.poten.Board.model.BoolResponse
import com.example.poten.dto.BoardForm
import com.example.poten.dto.SessionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @GET("oauth/kakao/{token}")
    fun getKakao(@Path("token") token : String): Call<SessionResponse>

    @POST("/api/users/keyword")

    fun postInterest(@Body interestList: HashMap<String, List<String>>):Call<BoolResponse>


}