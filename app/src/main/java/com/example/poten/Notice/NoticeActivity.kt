package com.example.poten.Notice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.poten.R
import com.example.poten.Utils.BottomNavigationViewHelper
import com.example.poten.databinding.ActivityLoginBinding
import com.example.poten.databinding.ActivityNoticeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class NoticeActivity : AppCompatActivity() {
    private val mContext: Context = this@NoticeActivity
    private val ACTIVITY_NUM = 1
    private lateinit var binding: ActivityNoticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        binding= ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)

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