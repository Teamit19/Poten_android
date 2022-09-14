package com.example.poten.Search

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.poten.Model.SearchNoticeItem
import com.example.poten.R
import com.example.poten.Utils.FirstFragment.FirstFragment
import com.example.poten.Utils.SearchFragment.ClubFragment
import com.example.poten.Utils.SearchFragment.NoticeFragment
import com.example.poten.Utils.SearchViewPagerAdapter
import com.example.poten.Utils.SecondFragment.SecondFragment
import com.google.android.material.tabs.TabLayout


class SearchActivity : AppCompatActivity() {

    private lateinit var toolbar : Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var searchView : SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewPager = findViewById(R.id.viewpager)
        setupViewPager(viewPager)

        tabLayout = findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)

        searchView = findViewById(R.id.searchview)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d("TAG", "검색 처리됨")
                return false;
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //var filteredItemList : List<SearchNoticeItem> = filter()
                return false
            }
        })
    }

    private fun setupViewPager(viewPager: ViewPager){
        var adapter : SearchViewPagerAdapter = SearchViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NoticeFragment(), "공고")
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
}