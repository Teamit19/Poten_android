package com.example.poten.Home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private lateinit var adapter: PostListViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mFrameLayout = findViewById<View>(R.id.container) as FrameLayout
        mRelativeLayout = findViewById<View>(R.id.relLayoutParent) as RelativeLayout


        var rv_postList: RecyclerView = findViewById<RecyclerView>(R.id.home_recyView) as RecyclerView
        // 리사이클러뷰 어댑터에 화면에 띄울 데이터를 넘긴다.
        adapter = PostListViewAdapter(getApplicationContext());

        // 어댑터 연결
        var manager = LinearLayoutManager (getApplicationContext())
        manager.reverseLayout = true
        manager.stackFromEnd = true

        rv_postList.layoutManager = manager
        rv_postList.setHasFixedSize(true)
        rv_postList.adapter = adapter

        // 하드코딩
        var postList =  mutableListOf<Post>()
        postList.add(Post("cookingTeens", "test1", "1분전", 1L, 2L, 1, 5, "contentsssssss"))

        // 데이터 연결
        adapter.setListData(postList)


        // 네비게이션 뷰
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