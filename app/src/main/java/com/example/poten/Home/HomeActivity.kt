package com.example.poten.Home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.poten.R
import com.example.poten.Utils.BottomNavigationViewHelper
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"
    private val mContext: Context = this@HomeActivity
    private val ACTIVITY_NUM = 0
    private val HOME_FRAGMENT = 1

    // widgets
    private var mFrameLayout: FrameLayout? = null
    private var mRelativeLayout: RelativeLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mFrameLayout = findViewById<View>(R.id.container) as FrameLayout
        mRelativeLayout = findViewById<View>(R.id.relLayoutParent) as RelativeLayout

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

    fun showLayout() {
        //Log.d(HomeActivity.TAG, "hideLayout: showing layout")
        mRelativeLayout?.setVisibility(View.VISIBLE)
        mFrameLayout!!.visibility = View.GONE
    }
    override fun onBackPressed() {
        super.onBackPressed()
        if (mFrameLayout?.getVisibility() == View.VISIBLE) {
            showLayout()
        }
    }
}