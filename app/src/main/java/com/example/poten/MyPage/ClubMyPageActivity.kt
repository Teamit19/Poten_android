package com.example.poten.MyPage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.poten.Model.memberList
import com.example.poten.Model.secondItem
import com.example.poten.R
import com.example.poten.Utils.FirstFragment.FirstFragment
import com.example.poten.Utils.MemberListAdapter
import com.example.poten.Utils.PagerFragmentStateAdapter
import com.example.poten.Utils.SecondFragment.SecondFragment
import com.example.poten.databinding.ActivityClubMyPageBinding
import com.example.poten.databinding.LayoutClubprofileBinding
import com.google.android.material.tabs.TabLayoutMediator

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
    private lateinit var viewPager2 : ViewPager2


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

    private val tabTitleArray = arrayOf(
        "공고",
        "활동"
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

        recyclerViewMemberlist = binding.layout.recyclerViewMembers

        btnFollow = binding.layout.btnFollow
        btnUnFollow = binding.layout.btnUnfollow
        btnVolunteer = binding.layout.btnVolunteer

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

        imgBackArror = binding.layout.backArrow
        imgBackArror.setOnClickListener(View.OnClickListener {
            Log.d("TAG", "onClick : navigating back to profile page")
            finish()
        })

        // FragmentStateAdapter 초기화
        val pagerAdapter = PagerFragmentStateAdapter(this)
            .apply{
                addFragment(FirstFragment())
                addFragment(SecondFragment())
            }

        viewPager2 = binding.layout.pager.apply{
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d("TAG", "Page ${position+1}")
                }
            })
        }

        TabLayoutMediator(binding.layout.tabLayout, viewPager2) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}