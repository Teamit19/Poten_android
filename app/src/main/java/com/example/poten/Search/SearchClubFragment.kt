package com.example.poten.Search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Board.model.ClubResponse
import com.example.poten.Board.model.ClubResponseList
import com.example.poten.Board.model.PosterResponse
import com.example.poten.Board.model.PosterResponseList
import com.example.poten.R
import com.example.poten.Utils.RetrofitClient
import com.example.poten.interfaces.ClubApi
import com.example.poten.interfaces.PosterApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchClubFragment : Fragment() {
    private lateinit var mContext: Context
    private var recyclerView: RecyclerView? = null
    private lateinit var adapter: SearchClubAdapter
    private val clubList =  ArrayList<ClubResponse>()
    private lateinit var keyword:String



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_poster, container, false)
        recyclerView = v.findViewById(R.id.recyclerView) as RecyclerView

        arguments?.let {
            keyword = it.getString("keyword").toString()
        }


        val layoutManger = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = layoutManger
        //recyclerView!!.addItemDecoration(DividerItemDecoration(recyclerView!!.context, layoutManger.orientation))
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var clubList = ArrayList<ClubResponse>()

        getSearchClub(keyword)

//        clubList.add(ClubResponse("한사랑클라이밍회 부원 모집하는중인...", "한사랑 클라이밍회", "#서울 #오프라인", "D-6", "profile1"))
//        clubList.add(ClubResponse("카메라를 사랑하는 모임 12기 부원 모...", "카사모", "#경기도 #오프라인", "D-23", "profile2"))
//        clubList.add(ClubResponse("주식공부 여기서회 8기 부원 모집중입...", "주식공부 여기서회", "#서울 #온라인", "D-23", "profile3"))
//        clubList.add(ClubResponse("한사랑클라이밍회 부원 모집하는중인...", "한사랑 클라이밍회", "#서울 #오프라인", "D-6", "profile1"))


//        adapter = SearchClubAdapter(clubList, mContext)
//        recyclerView!!.setAdapter(adapter)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context;

    }

    private fun getSearchClub(keyword : String) {
        var hashMap=HashMap<String, String>()
        hashMap.put("keyword", keyword)
        var retrofit = RetrofitClient.create(ClubApi::class.java, RetrofitClient.getAuth())
        Log.i("Club", "keyword 성공"+ keyword)


        retrofit.postSearchClub(hashMap).enqueue(object : Callback<ClubResponseList> {
            override fun onResponse(call: Call<ClubResponseList>, response: Response<ClubResponseList>) {
                Log.i("Club", "getSearchClub 성공"+ response.body().toString())

                clubList.clear() // 비우기
                response.body()?.clubResponseList?.let { it -> clubList.addAll(it) }

                // 어댑터 연결 - 마감임박 카드 뷰
                adapter = SearchClubAdapter(clubList, mContext)
                recyclerView!!.setAdapter(adapter)



            }

            override fun onFailure(call: Call<ClubResponseList>, t: Throwable) {
                Log.e("Club", "getSearchClub 실패"+t.message.toString())
            }
        })
    }

}