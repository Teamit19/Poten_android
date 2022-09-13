package com.example.poten.Board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.poten.Board.model.BoardResponse
import com.example.poten.R
import com.example.poten.Utils.RetrofitClient
import com.example.poten.dto.BoardForm
import com.example.poten.dto.SessionResponse
import com.example.poten.interfaces.BoardApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePostActivity : AppCompatActivity() {

    private lateinit var et_postContent : EditText
    private lateinit var et_postClubName : EditText
    private lateinit var btn_postSubmit : Button

    var retrofit = RetrofitClient.create(BoardApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        et_postContent = findViewById(R.id.et_postClubName)
        et_postClubName = findViewById(R.id.et_postClubName)
        btn_postSubmit = findViewById(R.id.btn_postSubmit)

        btn_postSubmit.setOnClickListener {
            Log.i("BOARD", "버튼 눌림")
            val content = et_postContent.text.toString()
            val clubName = et_postClubName.text.toString()

            val request = BoardForm(1L, content)
            saveBoard(request)
        }
    }

    private fun saveBoard(request: BoardForm) {
        retrofit.saveBoard(request).enqueue(object : Callback<BoardResponse> {
            override fun onResponse(call: Call<BoardResponse>, response: Response<BoardResponse>) {
                Log.i("BOARD", "save board 성공"+ response.body().toString())
            }

            override fun onFailure(call: Call<BoardResponse>, t: Throwable) {
                Log.e("BOARD", "save board 실패"+t.message.toString())
            }
        })
    }
}