package com.example.poten.interfaces

import retrofit2.Call
import com.example.poten.Board.model.BoardResponse
import com.example.poten.Board.model.BoardResponseList
import com.example.poten.dto.BoardForm
import okhttp3.MultipartBody
import retrofit2.http.*

interface BoardApi {
    // 업로드
    @POST("boards/upload")
    @Multipart
    fun saveBoard(@Part pics: MultipartBody.Part,
                  @Part("clubId") clubId: Long,
                  @Part("content") content: String): Call<BoardResponse>
//    fun saveBoard(@Part pics: MultipartBody.Part, @Part("dto") boardForm: BoardForm): Call<BoardResponse>
// fun saveBoard(@Body boardForm: BoardForm): Call<BoardResponse>

    // 조회
    @GET("boards/all")
    fun getBoardAll(): Call<BoardResponseList>
//
//    @GET("boards/{boardId}")
//    fun getBoard(): Call<Test>
//
//    @GET("boards/club/{clubId}")
//    fun getBoardByClub(): Call<Test>
//
//    @GET("boards/interest")
//    fun getBoardByInterest(): Call<Test>
//
//    @GET("boards/mypage")
//    fun getBoardByUser(): Call<Test>
//
//
//    // 수정/삭제
//    @PUT("boards/update/{boardId}")
//    fun updateBoard(): Call<Test>
//
//    @DELETE("boards/delete/{boardId}")
//    fun deleteBoard(): Call<Test>
//
//    // 기타
//    @POST("boards/{boardId}/heart")
//    fun heartBoard(): Call<Test>
//
//    @POST("boards/{boardId}/unheart")
//    fun unHeartBoard(): Call<Test>
//
//    @POST("boards/{boardId}/unheart")
//    fun unHeartBoard(): Call<Test>
//
//
//    // 댓글 CRUD
//    @GET("boards/{boardId}/comments")
//    fun saveComment(): Call<Test>
//
//    @GET("boards/mypage/comments")
//    fun getCommentByUser(): Call<Test>
//
//    @PUT("boards/comments/{commentId}")
//    fun updateComment(): Call<Test>
//
//    @DELETE("boards  /comments/{commentId}")
//    fun deleteComment(): Call<Test>
}
