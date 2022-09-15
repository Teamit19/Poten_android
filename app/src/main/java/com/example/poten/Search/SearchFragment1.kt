package com.example.poten.Search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.poten.R
import com.example.poten.Utils.SearchViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class SearchFragment1 : Fragment(){
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_search_1, container, false)
        viewPager = v.findViewById(R.id.viewpager)
        setupViewPager(viewPager)
        tabLayout = v.findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
        return v
    }

    fun setupViewPager(viewPager: ViewPager){
        var adapter : SearchViewPagerAdapter = SearchViewPagerAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(PopularClubFragment1(), "")
        adapter.addFragment(PopularClubFragment1(), "")
        adapter.addFragment(PopularClubFragment1(), "")

        viewPager.adapter = adapter
    }
}