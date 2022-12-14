package com.example.poten.MyPage

import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.net.Uri
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
import androidx.viewpager.widget.ViewPager
import com.example.poten.Board.model.ClubResponse
import com.example.poten.Model.memberList
import com.example.poten.R
import com.example.poten.Utils.MemberListAdapter
import com.example.poten.Utils.RetrofitClient
import com.example.poten.Utils.SearchViewPagerAdapter
import com.example.poten.Utils.SecondFragment.SecondFragment
import com.example.poten.databinding.ActivityClubMyPageBinding
import com.example.poten.interfaces.ClubApi
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
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

        // clubId ????????????
        val intent = getIntent() /*????????? ??????*/
//        clubId = 1
        clubId = intent.extras!!.getLong("clubId")


        // ????????? ?????? ????????????
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
            Log.d("????????????", "???????????? ??????")
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/rNreUdySBwCAcc5H6"))
            startActivity(intent)
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


    // ????????? ?????? ????????????
    private fun getClub(clubId : Long) {
        Log.i("CLUB", "getClub ?????????. clubId : " + clubId)
        var retrofit = RetrofitClient.create(ClubApi::class.java, RetrofitClient.getAuth())

        retrofit.getClub(clubId).enqueue(object : Callback<ClubResponse> {
            override fun onResponse(call: Call<ClubResponse>, response: Response<ClubResponse>) {
                Log.i("CLUB", "getClub ??????" + response.body().toString())
                Picasso.get()
                    .load("http://172.30.1.3:8080/files/images/"+ response?.body()?.profile?.fileName)
                    .into(binding.profilePhoto);

//                Picasso.get()
//                    .load("http://172.30.1.3:8080/files/images/"+ response?.body()?.background?.fileName)
//                    .into(binding.appbar);

                binding.tvOnoff.text = response?.body()?.activityType.toString()
                binding.tvArea.text = response?.body()?.region.toString()
                binding.clubname.text = response?.body()?.clubName
                binding.clubcomment.text = response?.body()?.clubDesc
                binding.tvFollowing.text = response?.body()?.followersNum.toString()
                binding.tvLike.text = response?.body()?.heartsNum.toString()
                binding.tvMenberCount.text = response?.body()?.membersNum.toString()

                //?????? ????????? ??????
                memberlist = java.util.ArrayList<memberList>()
                var userDataList = response?.body()?.members
                
                // ????????? ??????
                var manager = memberList(response?.body()?.manager?.name, image.get(0))
                memberlist.add(manager)

                // ?????? ??????
                for (i in userDataList?.indices!!) {                    
                    val member = memberList(userDataList[i].name, image.get(i%4))
                    
                    if(!userDataList[i].id.equals(response?.body()?.manager?.id)){
                        memberlist.add(member)  // ????????? ?????? ?????? ??????
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
                Log.e("CLUB", "getClub ??????" + t.message.toString())
            }
        })
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    private fun setupViewPager(viewPager: ViewPager){
        // ????????? fragment??? ?????? (clubId)
        val bundle = Bundle()
        bundle.putLong("clubId", clubId)

        var fragment1 = ClubPosterFragment()
        fragment1.arguments = bundle

        var fragment2 = SecondFragment()
        fragment2.arguments = bundle


        var adapter : SearchViewPagerAdapter = SearchViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(fragment1, "??????")
        adapter.addFragment(fragment2 , "??????")

        viewPager.adapter = adapter
    }
}