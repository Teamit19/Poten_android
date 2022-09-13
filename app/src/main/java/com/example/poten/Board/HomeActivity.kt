package com.example.poten.Board

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.R
import com.example.poten.Utils.BottomNavigationViewHelper
import com.example.poten.Utils.RetrofitClient
import com.example.poten.interfaces.BoardApi
import com.example.poten.interfaces.UserApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kakao.sdk.common.util.Utility

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"
    private val mContext: Context = this@HomeActivity
    private val ACTIVITY_NUM = 0
    private val HOME_FRAGMENT = 1

    private lateinit var floatBtn : FloatingActionButton
//    private lateinit var spinnerDirectory : Spinner
    // widgets
    private var mFrameLayout: FrameLayout? = null
    private var mRelativeLayout: RelativeLayout? = null

    private lateinit var adapter: PostListViewAdapter

    var retrofit = RetrofitClient.create(BoardApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        mFrameLayout = findViewById<View>(R.id.container) as FrameLayout
        mRelativeLayout = findViewById<View>(R.id.relLayoutParent) as RelativeLayout
        floatBtn = findViewById(R.id.floatBtn)
//        spinnerDirectory = findViewById(R.id.spinnerDirectory)

        // 스피너(드롭다운) 연결
//        var spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, R.layout.snippet_top_feed_toolbar)
//        spinnerDirectory.adapter = spinnerAdapter


        // 리사이클러뷰 어댑터에 화면에 띄울 데이터를 넘긴다.
        var rv_postList: RecyclerView = findViewById<RecyclerView>(R.id.home_recyView) as RecyclerView
        adapter = PostListViewAdapter(getApplicationContext());

        // 피드 작성 _ 플로팅 버튼 연결
        floatBtn.setOnClickListener{
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }

        // 어댑터 연결
        var manager = LinearLayoutManager (getApplicationContext())
        manager.reverseLayout = true
        manager.stackFromEnd = true

        rv_postList.layoutManager = manager
        rv_postList.setHasFixedSize(true)
        rv_postList.adapter = adapter

//        // 피드 데이터 불러오기
//        retrofit.saveBoard()


        // 피드 데이터 불러오기 - 하드코딩
        var postList =  mutableListOf<Post>()
        postList.add(Post("cookingTeens", "test1", "1분전", 1L, 2L, 1, 5, "contentsssssss"))
        postList.add(Post("cookingTeens", "test2", "10분전", 1L, 2L, 1, 5, "쿠킹틴즈 5기 최고\n2조 친구들이랑 모여서 서브 샌드위치를 만들어 먹었다앙 에어프라이어에 캄튀도 만들어봤는데 너무 맛있어서 다들."))
        postList.add(Post("cookingTeens", "test1", "1분전", 1L, 2L, 1, 5, "contentsssssss"))
        postList.add(Post("cookingTeens", "test2", "10분전", 1L, 2L, 1, 5, "쿠킹틴즈 5기 최고\n2조 친구들이랑 모여서 서브 샌드위치를 만들어 먹었다앙 에어프라이어에 캄튀도 만들어봤는데 너무 맛있어서 다들."))
        postList.add(Post("cookingTeens", "test1", "1분전", 1L, 2L, 1, 5, "contentsssssss"))
        postList.add(Post("cookingTeens", "test2", "10분전", 1L, 2L, 1, 5, "쿠킹틴즈 5기 최고\n2조 친구들이랑 모여서 서브 샌드위치를 만들어 먹었다앙 에어프라이어에 캄튀도 만들어봤는데 너무 맛있어서 다들."))
        postList.add(Post("cookingTeens", "test1", "1분전", 1L, 2L, 1, 5, "contentsssssss"))
        postList.add(Post("cookingTeens", "test2", "10분전", 1L, 2L, 1, 5, "쿠킹틴즈 5기 최고\n2조 친구들이랑 모여서 서브 샌드위치를 만들어 먹었다앙 에어프라이어에 캄튀도 만들어봤는데 너무 맛있어서 다들."))

        adapter.setListData(postList)


        // 네비게이션 뷰
        setupBottomNavigationView()
        setupToolbarView()

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

    private fun setupToolbarView() {
//        val spinner = findViewById<Spinner>(R.id.spinnerDirectory) as Spinner
//        var spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, R.layout.snippet_top_feed_toolbar)
//        var spinnerData = resources.getStringArray(R.array.spinner_array)
//        var spinnerAdapter = ArrayAdapter<String>(getApplicationContext(), R.layout.activity_home, spinnerData)

//
//        Log.d("SPINNER1", spinner.toString())
//        Log.d("SPINNER2", spinnerAdapter.toString())
//        spinner.adapter = spinnerAdapter
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