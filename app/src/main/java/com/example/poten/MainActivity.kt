package com.example.poten

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.poten.Board.HomeActivity
import com.example.poten.MyPage.ClubMyPageActivity
import com.example.poten.dto.Test
import com.example.poten.interfaces.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var button0 : Button
    lateinit var button1 : Button
    lateinit var restText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit = Retrofit.Builder().baseUrl("http://192.168.0.7:8080/").addConverterFactory(GsonConverterFactory.create()).build();
        val service = retrofit.create(RetrofitService::class.java);

        button0 = findViewById(R.id.button)
        button1 = findViewById(R.id.button1)
        restText = findViewById(R.id.text_rest_api)

        service.getTest().enqueue(object: Callback<Test> {
            override fun onResponse(call: Call<Test>, response: Response<Test>) {
                if(response.isSuccessful){
                    var result: Test? = response.body();
                    println("success" + result.toString());
                    restText.setText(result.toString());
                }else{
                    println("여기ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ 실패");
                }
            }

            override fun onFailure(call: Call<Test>, t: Throwable) {
                println("여기ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ 실패222");
                Log.d("YMC", "onFailure 에러: " + t.message.toString());
            }
        })

        button0.setOnClickListener(View.OnClickListener {
            val intent1 = Intent(this, HomeActivity::class.java) //ACTIVITY_NUM = 0

            startActivity(intent1)
        })

        button1.setOnClickListener(View.OnClickListener {
            val intent1 = Intent(this, ClubMyPageActivity::class.java) //ACTIVITY_NUM = 0

            startActivity(intent1)
        })

    }


}