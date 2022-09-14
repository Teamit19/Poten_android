package com.example.poten.MyPage

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.poten.Model.memberList
import com.example.poten.R
import com.example.poten.Utils.FirstFragment.FirstFragment
import com.example.poten.Utils.MemberListAdapter
import com.example.poten.Utils.SearchViewPagerAdapter
import com.example.poten.Utils.SecondFragment.SecondFragment
import com.example.poten.databinding.ActivityClubMyPageBinding
import com.google.android.material.tabs.TabLayout

class ClubMyPageActivity : AppCompatActivity() {
    private val mContext: Context = this@ClubMyPageActivity

    private lateinit var btnFollow : TextView
    private lateinit var btnUnFollow : TextView
    private lateinit var btnVolunteer : TextView

    // memgerlist recyclerview
    private lateinit var memberlist : ArrayList<memberList>
    private lateinit var recyclerViewMemberlist: RecyclerView
    private var memberListAdapter: MemberListAdapter? = null

    // tablayout and viewpager2
    private lateinit var viewPager : ViewPager
    private lateinit var tablayout : TabLayout


    // back press
    private lateinit var imgBackArror : ImageView

    private val image = arrayOf<Int>(
        R.drawable.ic_account_circle,
        R.drawable.ic_account_circle,
        R.drawable.ic_account_circle,
        R.drawable.ic_account_circle,
        R.drawable.ic_account_circle
    )
    private val title = arrayOf<String>(
        "USER01","USER02","USER03","USER04","USER05"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_club_my_page)

        //binding SetUp
        val binding = (DataBindingUtil.setContentView(
            this, R.layout.activity_club_my_page) as ActivityClubMyPageBinding)
            .apply {
                lifecycleOwner = this@ClubMyPageActivity
            }

        recyclerViewMemberlist = binding.recyclerViewMembers

        btnFollow = binding.btnFollow
        btnUnFollow = binding.btnUnfollow
        btnVolunteer = binding.btnVolunteer

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(mContext)
        recyclerViewMemberlist.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        recyclerViewMemberlist.setItemAnimator(DefaultItemAnimator())

        memberlist = java.util.ArrayList<memberList>()

        for (i in image.indices) {
            val member = memberList(title[i], image.get(i))
            memberlist.add(member)
        }

        memberListAdapter = MemberListAdapter(mContext, memberlist)
        recyclerViewMemberlist.setAdapter(memberListAdapter)

        btnFollow.setOnClickListener(View.OnClickListener{
            btnUnFollow.setVisibility(View.VISIBLE)
            btnFollow.setVisibility(View.INVISIBLE)
        })

        btnUnFollow.setOnClickListener(View.OnClickListener{
            btnFollow.setVisibility(View.VISIBLE)
            btnUnFollow.setVisibility(View.INVISIBLE)
        })

        btnVolunteer.setOnClickListener(View.OnClickListener {
            Log.d("지원하기", "지원하기 눌림")
        })


        imgBackArror = binding.backArrow
        imgBackArror.setOnClickListener(View.OnClickListener {
            Log.d("TAG", "onClick : navigating back to profile page")
            finish()
        })

        viewPager = binding.pager

        var display = windowManager.defaultDisplay;
        var size = Point()
        display.getSize(size)
        viewPager.layoutParams.height = 1000
        setupViewPager(viewPager)

        tablayout = binding.tabLayout
        tablayout.setupWithViewPager(viewPager)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    private fun setupViewPager(viewPager: ViewPager){
        var adapter : SearchViewPagerAdapter = SearchViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FirstFragment(), "공고")
        adapter.addFragment(SecondFragment(), "활동")

        viewPager.adapter = adapter
    }
}