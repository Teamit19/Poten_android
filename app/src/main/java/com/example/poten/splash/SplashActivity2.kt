package com.example.poten.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.poten.Login.LoginActivity
import com.example.poten.R

class SplashActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)


        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        },DURATION)

//
//        val intent = Intent(this, SplashActivity2::class.java)
//        startActivity(intent);
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//        finish();


    }
    companion object {
        private const val DURATION : Long = 1500
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}