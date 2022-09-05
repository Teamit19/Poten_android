package com.example.poten

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.poten.Home.HomeActivity

class MainActivity : AppCompatActivity() {
    lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)

        button.setOnClickListener(View.OnClickListener {
            val intent1 = Intent(this, HomeActivity::class.java) //ACTIVITY_NUM = 0

            startActivity(intent1)
        })

    }


}