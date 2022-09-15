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


class SearchFragment2 : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var result:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            result = it.getString("result").toString()
            println("Fragment로 넘어옴" + result)
        }
        var v: View = inflater.inflate(R.layout.fragment_search_2, container, false)
        viewPager = v.findViewById(R.id.viewpager)
        setupViewPager(viewPager)
        tabLayout = v.findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)

        return v
    }

    fun setupViewPager(viewPager: ViewPager){
        val bundle = Bundle()
        bundle.putString("keyword", result)
        var fragment1 = NoticeFragment2()
        fragment1.arguments = bundle

        var fragment2 = SearchClubFragment()
        fragment2.arguments = bundle


        var adapter : SearchViewPagerAdapter = SearchViewPagerAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(fragment1, "공고")
        adapter.addFragment(fragment2, "동아리")

        viewPager.adapter = adapter
    }

}