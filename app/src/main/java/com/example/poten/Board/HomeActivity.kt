package com.example.poten.Board

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Board.model.BoardResponse
import com.example.poten.Board.model.BoardResponseList
import com.example.poten.R
import com.example.poten.Utils.BottomNavigationViewHelper
import com.example.poten.Utils.RetrofitClient
import com.example.poten.dto.BoardForm
import com.example.poten.interfaces.BoardApi
import com.example.poten.interfaces.UserApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kakao.sdk.common.util.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"
    private val mContext: Context = this@HomeActivity
    private val ACTIVITY_NUM = 0
    private val HOME_FRAGMENT = 1

    private lateinit var floatBtn : FloatingActionButton
    private lateinit var spinner : Spinner

    // widgets
    private var mFrameLayout: FrameLayout? = null
    private var mRelativeLayout: RelativeLayout? = null

    private lateinit var adapter: PostListViewAdapter

    var retrofit = RetrofitClient.create(BoardApi::class.java)
    private val postList =  mutableListOf<BoardResponse>()
    private lateinit var rv_postList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)

        mFrameLayout = findViewById<View>(R.id.container) as FrameLayout
        mRelativeLayout = findViewById<View>(R.id.relLayoutParent) as RelativeLayout
        floatBtn = findViewById(R.id.floatBtn)
        spinner = findViewById(R.id.spinnerDirectory)


        // 스피너(드롭다운) 연결
        ArrayAdapter.createFromResource(
            this,
            R.array.home_spinner_array,
            R.layout.home_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        // 스피너 리스너 연결 및 구현 (피드 데이터 불러오기)
        spinner.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.i("SPINNER", "onItemClick" + position.toString())
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position : Int, p3: Long) {
                Log.i("SPINNER", "onItemSelected position" + position.toString())
                if (position == 0){
                    getBoardByInterest()
                } else if (position == 1){
                    getBoardByFollow()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.i("SPINNER", "onNothingSelected position")
                getBoardAll()
            }
        }


        // 리사이클러뷰 어댑터에 화면에 띄울 데이터를 넘긴다.
        rv_postList = findViewById<RecyclerView>(R.id.home_recyView) as RecyclerView
        adapter = PostListViewAdapter(getApplicationContext());

        // 피드 작성 _ 플로팅 버튼 연결
        floatBtn.setOnClickListener{
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }

        // 기본 피드 데이터 불러오기
        getBoardAll()

        // 네비게이션 뷰
        setupBottomNavigationView()
        setupToolbarView()

    }

    // (스피너 기본 설정) 서버에서 모든 게시물 불러오기
    private fun getBoardAll() {
        Log.i("BOARD", "getBoardAll 호출됨")
        var retrofit = RetrofitClient.create(BoardApi::class.java,RetrofitClient.getAuth())

        retrofit.getBoardAll().enqueue(object : Callback<BoardResponseList> {
            override fun onResponse(call: Call<BoardResponseList>, response: Response<BoardResponseList>) {
                Log.i("BOARD", "getBoardAll 성공"+ response.body().toString())

                postList.clear() // 비우기
                response.body()?.boardResponseList?.let { it -> postList.addAll(it) }

                // 어댑터 연결
                var manager = LinearLayoutManager (getApplicationContext())
                manager.reverseLayout = true
                manager.stackFromEnd = true

                rv_postList.layoutManager = manager
                rv_postList.setHasFixedSize(true)
                rv_postList.adapter = adapter
                adapter.setListData(postList)
            }

            override fun onFailure(call: Call<BoardResponseList>, t: Throwable) {
                Log.e("BOARD", "getBoardAll 실패"+t.message.toString())
            }
        })
    }

    // (스피너 설정 1)서버에서 팔로잉한 피드 불러오기
    private fun getBoardByInterest() {
        Log.i("BOARD", "getBoardByInterest 호출됨")
        var retrofit = RetrofitClient.create(BoardApi::class.java,RetrofitClient.getAuth())

        retrofit.getBoardByInterest().enqueue(object : Callback<BoardResponseList> {
            override fun onResponse(call: Call<BoardResponseList>, response: Response<BoardResponseList>) {
                Log.i("BOARD", "getBoardByInterest 성공"+ response.body().toString())

                postList.clear() // 비우기
                response.body()?.boardResponseList?.let { it -> postList.addAll(it) }

                // 어댑터 연결
                var manager = LinearLayoutManager (getApplicationContext())
                manager.reverseLayout = true
                manager.stackFromEnd = true

                rv_postList.layoutManager = manager
                rv_postList.setHasFixedSize(true)
                rv_postList.adapter = adapter
                adapter.setListData(postList)
            }

            override fun onFailure(call: Call<BoardResponseList>, t: Throwable) {
                Log.e("BOARD", "getBoardByInterest 실패"+t.message.toString())
            }
        })
    }

    // (스피너 설정 2)서버에서 팔로잉한 피드 불러오기
    private fun getBoardByFollow() {
        Log.i("BOARD", "getBoardByFollow 호출됨")
        var retrofit = RetrofitClient.create(BoardApi::class.java,RetrofitClient.getAuth())

        retrofit.getBoardByFollow().enqueue(object : Callback<BoardResponseList> {
            override fun onResponse(call: Call<BoardResponseList>, response: Response<BoardResponseList>) {
                Log.i("BOARD", "getBoardByFollow 성공"+ response.body().toString())

                postList.clear() // 비우기
                response.body()?.boardResponseList?.let { it -> postList.addAll(it) }

                // 어댑터 연결
                var manager = LinearLayoutManager (getApplicationContext())
                manager.reverseLayout = true
                manager.stackFromEnd = true

                rv_postList.layoutManager = manager
                rv_postList.setHasFixedSize(true)
                rv_postList.adapter = adapter
                adapter.setListData(postList)
            }

            override fun onFailure(call: Call<BoardResponseList>, t: Throwable) {
                Log.e("BOARD", "getBoardByFollow 실패"+t.message.toString())
            }
        })
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
