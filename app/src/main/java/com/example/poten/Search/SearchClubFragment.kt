package com.example.poten.Search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.poten.R

class SearchClubFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_search_1, container, false)
//        viewPager = v.findViewById(R.id.viewpager)
//        setupViewPager(viewPager)
//        tabLayout = v.findViewById(R.id.tabLayout)
//        tabLayout.setupWithViewPager(viewPager)
        return v
    }
}