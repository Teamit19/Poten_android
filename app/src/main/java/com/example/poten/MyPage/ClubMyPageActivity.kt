package com.example.poten.MyPage

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.poten.Board.model.ClubResponse
import com.example.poten.Model.memberList
import com.example.poten.Notice.PosterFragment
import com.example.poten.R
import com.example.poten.Utils.FirstFragment.FirstFragment
import com.example.poten.Utils.MemberListAdapter
import com.example.poten.Utils.RetrofitClient
import com.example.poten.Utils.SearchViewPagerAdapter
import com.example.poten.Utils.SecondFragment.SecondFragment
import com.example.poten.databinding.ActivityClubMyPageBinding
import com.example.poten.interfaces.ClubApi
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClubMyPageActivity : AppCompatActivity() {
    private val mContext: Context = this@ClubMyPageActivity

    // clubID
    private var clubId : Long = -1

    // button
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

    // binding
    private lateinit var binding : ActivityClubMyPageBinding

    // back press
    private lateinit var imgBackArror : ImageView

    private val image = arrayOf<Int>(
        R.drawable.ic_account_circle01,
        R.drawable.ic_account_circle02,
        R.drawable.ic_account_circle03,
        R.drawable.ic_account_circle04
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_club_my_page)

        // clubId 받아오기
        clubId = 1

        // 동아리 정보 불러오기
        getClub (clubId)

        //binding SetUp
        binding = (DataBindingUtil.setContentView(
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

        btnFollow.setOnClickListener(View.OnClickListener{
            btnUnFollow.setVisibility(View.VISIBLE)
            btnFollow.setVisibility(View.INVISIBLE)
            binding.tvFollowing.text = binding.tvFollowing.text.toString().toInt().plus(1).toString()
        })

        btnUnFollow.setOnClickListener(View.OnClickListener{
            btnFollow.setVisibility(View.VISIBLE)
            btnUnFollow.setVisibility(View.INVISIBLE)
            binding.tvFollowing.text = binding.tvFollowing.text.toString().toInt().minus(1).toString()
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
    } // end OnCreate


    // 동아리 정보 불러오기
    private fun getClub(clubId : Long) {
        Log.i("CLUB", "getClub 호출됨. clubId : " + clubId)
        var retrofit = RetrofitClient.create(ClubApi::class.java, RetrofitClient.getAuth())

        retrofit.getClub(clubId).enqueue(object : Callback<ClubResponse> {
            override fun onResponse(call: Call<ClubResponse>, response: Response<ClubResponse>) {
                Log.i("CLUB", "getClub 성공" + response.body().toString())
                binding.tvOnoff.text = response?.body()?.activityType.toString()
                binding.tvArea.text = response?.body()?.region.toString()
                binding.clubname.text = response?.body()?.clubName
                binding.clubcomment.text = response?.body()?.clubDesc
                binding.tvFollowing.text = response?.body()?.followersNum.toString()
                binding.tvLike.text = response?.body()?.heartsNum.toString()
                binding.tvMenberCount.text = response?.body()?.membersNum.toString()

                //멤버 리스트 연결
                memberlist = java.util.ArrayList<memberList>()
                var userDataList = response?.body()?.members
                
                // 메니저 연결
                var manager = memberList(response?.body()?.manager?.name, image.get(0))
                memberlist.add(manager)

                // 일반 회원
                for (i in userDataList?.indices!!) {                    
                    val member = memberList(userDataList[i].name, image.get(i%4))
                    
                    if(!userDataList[i].id.equals(response?.body()?.manager?.id)){
                        memberlist.add(member)  // 메니저 중복 예외 처리
                    }                    
//                    val member1 = memberList("test0", image.get(1))
//                    val member2 = memberList("test1", image.get(2))
//                    val member3 = memberList("test2", image.get(3))
//                    val member4 = memberList("test3", image.get(0))
//                    memberlist.add(member1)
//                    memberlist.add(member2)
//                    memberlist.add(member3)
//                    memberlist.add(member4)
                }

                memberListAdapter = MemberListAdapter(mContext, memberlist)
                recyclerViewMemberlist.setAdapter(memberListAdapter)

            }

            override fun onFailure(call: Call<ClubResponse>, t: Throwable) {
                Log.e("CLUB", "getClub 실패" + t.message.toString())
            }
        })
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    private fun setupViewPager(viewPager: ViewPager){
        // 데이터 fragment에 전달 (clubId)
        val bundle = Bundle()
        bundle.putLong("clubId", clubId)

        var fragment1 = ClubPosterFragment()
        fragment1.arguments = bundle

        var fragment2 = SecondFragment()
        fragment2.arguments = bundle


        var adapter : SearchViewPagerAdapter = SearchViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(fragment1, "공고")
        adapter.addFragment(fragment2 , "활동")

        viewPager.adapter = adapter
    }
}