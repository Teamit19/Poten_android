package com.example.poten.Search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.poten.Login.RecyclerViewDecoration
import com.example.poten.Notice.SpaceDecoration
import com.example.poten.R
import com.example.poten.Utils.SearchViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class SearchFragment1 : Fragment(){
    private lateinit var mContext: Context
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private var recyclerView: RecyclerView? = null
    var categoryList = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_search_1, container, false)

        recyclerView = v.findViewById(R.id.popular_search_word) as RecyclerView
//        val layoutManger = LinearLayoutManager(activity)
//        recyclerView!!.layoutManager = layoutManger
        recyclerView!!.addItemDecoration(KeywordDecoration(4))
        recyclerView!!.layoutManager= GridLayoutManager(activity, 4)

        initRecycler()
        viewPager = v.findViewById(R.id.viewpager)
        setupViewPager(viewPager)
        tabLayout = v.findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
        return v
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context;

    }

    fun setupViewPager(viewPager: ViewPager){
        var adapter : SearchViewPagerAdapter = SearchViewPagerAdapter(requireActivity().supportFragmentManager)
        adapter.addFragment(PopularClubFragment1(), "")
        adapter.addFragment(PopularClubFragment1(), "")
        adapter.addFragment(PopularClubFragment1(), "")

        viewPager.adapter = adapter
    }

    private fun initRecycler() {

        var categoryAdapter=KeywordAdapter(mContext)

        categoryList.addAll(listOf("독서토론", "밴드부", "주식투자", "풋살", "크리에이터", "댄스"))

//        var categoryAdapter = CategoryAdapter(this)
        categoryAdapter.list=categoryList
        recyclerView!!.adapter=categoryAdapter
        categoryAdapter.setOnItemClickListener(object : KeywordAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {

//                clickInterest.add(datas[position])
            }

        })
//        binding.recycleViewCategory.addItemDecoration(SpaceDecoration())
//        binding.recycleViewCategory.adapter = categoryAdapter
//        binding.recycleViewCategory.layoutManager= LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
//

    }




}