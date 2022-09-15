package com.example.poten.Search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.poten.Model.SearchNoticeItem
import com.example.poten.R
import com.example.poten.Utils.BottomNavigationViewHelper
import com.example.poten.Utils.SearchFragment.ClubFragment
import com.example.poten.Utils.SearchFragment.NoticeFragment2
import com.example.poten.Utils.SearchViewPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout


class SearchActivity : AppCompatActivity() {

    private val mContext: Context = this@SearchActivity
    private val ACTIVITY_NUM = 2

    private lateinit var toolbar : Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var searchView : SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_view, SearchFragment1()).commit()
        //viewPager = findViewById(R.id.viewpager)
        //setupViewPager(viewPager)

        //tabLayout = findViewById(R.id.tabLayout)
        //tabLayout.setupWithViewPager(viewPager)

        searchView = findViewById(R.id.searchview)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d("TAG", "검색 처리됨")
                println("검색 결과:  " + query)
                val bundle: Bundle = Bundle()
                var frag2 = SearchFragment2()
                bundle.putString("result", query)
                frag2.arguments = bundle

                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_view, frag2).commit()

                return false;
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //var filteredItemList : List<SearchNoticeItem> = filter()
                return false
            }
        })


        // 네비게이션 뷰
        setupBottomNavigationView()

    }

    private fun setupViewPager(viewPager: ViewPager){
        var adapter : SearchViewPagerAdapter = SearchViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NoticeFragment2(), "공고")
        adapter.addFragment(ClubFragment(), "동아리")

        viewPager.adapter = adapter
    }

    private fun filter(items: List<SearchNoticeItem>, query: String): List<SearchNoticeItem>? {
        var query = query
        query = query.toLowerCase()
        val filteredItemList: MutableList<SearchNoticeItem> = ArrayList()
        for (item in items) {
            val text = item.name.toLowerCase()
            if (text.contains(query)) {
                filteredItemList.add(item)
            }
        }
        return filteredItemList
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