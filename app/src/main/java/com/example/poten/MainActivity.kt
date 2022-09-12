package com.example.poten

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.poten.Home.HomeActivity
import com.example.poten.MyPage.ClubMyPageActivity
import com.example.poten.Notice.NoticeActivity

class MainActivity : AppCompatActivity() {
    lateinit var button0 : Button
    lateinit var button1 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button0 = findViewById(R.id.button)
        button1 = findViewById(R.id.button1)

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