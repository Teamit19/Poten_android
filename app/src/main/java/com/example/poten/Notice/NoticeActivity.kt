package com.example.poten.Notice

import android.content.Context
import SingleAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.poten.Board.model.PosterResponse
import com.example.poten.Board.model.PosterResponseList
import com.example.poten.R
import com.example.poten.Search.PosterAdapter
import com.example.poten.Utils.BottomNavigationViewHelper
import com.example.poten.Utils.RetrofitClient
import com.example.poten.Utils.SearchViewPagerAdapter
import com.example.poten.databinding.ActivityNoticeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.poten.interfaces.PosterApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeActivity : AppCompatActivity() {
    private val mContext: Context = this@NoticeActivity
    private val ACTIVITY_NUM = 1
    private lateinit var binding: ActivityNoticeBinding
    var categoryList = mutableListOf<String>()
    private val postList =  mutableListOf<PosterResponse>()
    var retrofit = RetrofitClient.create(PosterApi::class.java)
    private lateinit var cardAdapter: CardAdapter
    private lateinit var posterAdapter: PosterAdapter

    private lateinit var spinner : Spinner
    private lateinit var smallSpinner : Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        binding= ActivityNoticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializelist()
        initRecycler()

        binding.viewpager.layoutParams.height=1000
        setupViewPager(binding.viewpager)
        binding.tabs.setupWithViewPager(binding.viewpager)

        getPosterAll()
        setSpinner()

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
        bottomNavigationViewEx.itemIconTintList = null
    }

    private fun setSpinner() {
        spinner = findViewById(R.id.spinnerDirectory)
        smallSpinner = findViewById(R.id.spinnerPoster)

        ArrayAdapter.createFromResource(
            this, R.array.home_spinner_area,R.layout.notice_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.notice_spinner_poster,
            R.layout.home_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            smallSpinner.adapter = adapter
        }
    }

    private fun getPosterAll() {
        retrofit.getPosterAll().enqueue(object : Callback<PosterResponseList> {
            override fun onResponse(call: Call<PosterResponseList>, response: Response<PosterResponseList>) {
                Log.i("Poster", "getPosterAll ??????"+ response.body().toString())

                postList.clear() // ?????????
                response.body()?.posterResponseList?.let { it -> postList.addAll(it) }

                // ????????? ?????? - ???????????? ?????? ???
                cardAdapter=CardAdapter(applicationContext)
                binding.recycleViewDeadline.addItemDecoration(SpaceDecoration())
                binding.recycleViewDeadline.adapter = cardAdapter
                binding.recycleViewDeadline.layoutManager= LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)

                cardAdapter.datas = postList

                //????????? ?????? - ?????? ??????
//                posterAdapter= PosterAdapter(applicationContext)
//                binding.recycleViewDeadline.addItemDecoration(SpaceDecoration())
//                binding.recycleViewDeadline.adapter = posterAdapter
//                binding.recycleViewDeadline.layoutManager= LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
//
//                posterAdapter.datas = postList


            }

            override fun onFailure(call: Call<PosterResponseList>, t: Throwable) {
                Log.e("Poster", "getPosterAll ??????"+t.message.toString())
            }
        })
    }

    private fun initRecycler() {

        var categoryAdapter=SingleAdapter(this)


//        var categoryAdapter = CategoryAdapter(this)
        categoryAdapter.list=categoryList
        categoryAdapter.setOnItemClickListener(object : SingleAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {

//                clickInterest.add(datas[position])
            }

        })
        binding.recycleViewCategory.addItemDecoration(SpaceDecoration())
        binding.recycleViewCategory.adapter = categoryAdapter
        binding.recycleViewCategory.layoutManager= LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)


    }

    private fun setupViewPager(viewPager: ViewPager){
        var adapter : SearchViewPagerAdapter = SearchViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(PosterFragment(), "??????")
        adapter.addFragment(PosterFragment(), "?????????")
        adapter.addFragment(PosterFragment(), "????????????")


        viewPager.adapter = adapter
    }
    fun initializelist() {
        categoryList.addAll(listOf("??????", "??????/??????", "??????", "IT", "?????????", "??????", "?????????"))
    }

}