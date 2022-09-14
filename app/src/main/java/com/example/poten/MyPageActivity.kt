package com.example.poten

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.poten.MyPage.ClubMyPageActivity
import com.example.poten.Utils.BottomNavigationViewHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyPageActivity: AppCompatActivity(){
    private val mContext: Context = this@MyPageActivity
    private val ACTIVITY_NUM = 3
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        button = findViewById(R.id.clubProfile)
        button.setOnClickListener(View.OnClickListener {
            val intent1 = Intent(this, ClubMyPageActivity::class.java) //ACTIVITY_NUM = 0
            startActivity(intent1)
        })

        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        //Log.d(HomeActivity.TAG, "setupBottomNavigationView: setting up BottomNavigationView")
        val bottomNavigationViewEx =
            findViewById<View>(R.id.bottomNavViewBar) as BottomNavigationView
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx)
        BottomNavigationViewHelper.enableNavigation(mContext, this, bottomNavigationViewEx)
        val menu = bottomNavigationViewEx.menu
        val menuItem = menu.getItem(ACTIVITY_NUM)
        menuItem.isChecked = true
    }
}