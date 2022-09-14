package com.example.poten.interfaces

import com.example.poten.Board.model.BoolResponse
import com.example.poten.Board.model.UserResponse
import com.example.poten.dto.SessionResponse
import com.example.poten.dto.SignUpForm
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @GET("oauth/kakao/{token}")
    fun getKakao(@Path("token") token : String): Call<SessionResponse>

    @GET("/api/users")
    fun getUserInfo(): Call<UserResponse>

    @POST("/api/users/keyword")
    fun postInterest(@Body interestList: HashMap<String, Set<String>>):Call<BoolResponse>

    @POST("/api/users")
    fun postInfo(@Body signUpForm: SignUpForm):Call<BoolResponse>

}