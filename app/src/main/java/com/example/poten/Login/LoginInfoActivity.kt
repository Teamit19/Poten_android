package com.example.poten.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.poten.Board.model.BoolResponse
import com.example.poten.Board.model.UserResponse
import com.example.poten.MainActivity
import com.example.poten.Utils.RetrofitClient
import com.example.poten.databinding.ActivityLoginBinding
import com.example.poten.databinding.ActivityLoginInfoBinding
import com.example.poten.dto.SignUpForm
import com.example.poten.interfaces.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginInfoBinding

    var retrofit = RetrofitClient.create(UserApi::class.java, RetrofitClient.getAuth())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setEmail()
        binding.btnNext.setOnClickListener {
            setNext()
            val intent = Intent(this,  SelectAreaActivity::class.java)
            startActivity(intent)
        }


    }

    fun setNext() {
        val email=binding.inputEmail.text.toString()
        val name=binding.inputName.text.toString()
        val request=SignUpForm(email, name)

        retrofit.postInfo(request).enqueue(object : Callback<BoolResponse> {
            override fun onResponse(call: Call<BoolResponse>, response: Response<BoolResponse>) {
                Log.i("user", "postInfo 성공"+ response.body().toString())

            }

            override fun onFailure(call: Call<BoolResponse>, t: Throwable) {
                Log.e("user", "postInfo 실패"+t.message.toString())
            }
        })
    }


    fun setEmail() {
        retrofit.getUserInfo().enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.i("User", "getUserInfo 성공"+ response.body().toString())
                binding.inputEmail.setText(response.body()?.email)
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("User", "getUserInfo 실패"+t.message.toString())
            }
        })
    }




}