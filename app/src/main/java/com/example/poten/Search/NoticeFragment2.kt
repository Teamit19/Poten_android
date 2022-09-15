package com.example.poten.Search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Board.model.PosterResponse
import com.example.poten.Board.model.PosterResponseList
import com.example.poten.R
import com.example.poten.Utils.RetrofitClient
import com.example.poten.interfaces.PosterApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticeFragment2 : Fragment() {
    private lateinit var mContext: Context
    private var recyclerView: RecyclerView? = null
    private lateinit var adapter: PosterAdapter
    var retrofit = RetrofitClient.create(PosterApi::class.java)
    private val postList =  ArrayList<PosterResponse>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_poster, container, false)

        recyclerView = v.findViewById(R.id.recyclerView) as RecyclerView

        val layoutManger = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = layoutManger
        recyclerView!!.addItemDecoration(DividerItemDecoration(recyclerView!!.context, layoutManger.orientation))

        return v
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context;

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPosterAll()


//        postList.add(PopularClubResponse("한사랑클라이밍회 부원 모집하는중인...", "한사랑 클라이밍회", "#서울 #오프라인", "D-6", "profile1", "post1"))
//        postList.add(PopularClubResponse("카메라를 사랑하는 모임 12기 부원 모...", "카사모", "#경기도 #오프라인", "D-23", "profile2", "post2"))
//        postList.add(PopularClubResponse("주식공부 여기서회 8기 부원 모집중입...", "주식공부 여기서회", "#서울 #온라인", "D-23", "profile3", "post3"))
//        postList.add(PopularClubResponse("한사랑클라이밍회 부원 모집하는중인...", "한사랑 클라이밍회", "#서울 #오프라인", "D-6", "profile1", "post1"))
//




    }

    private fun getPosterAll() {
        retrofit.getPosterAll().enqueue(object : Callback<PosterResponseList> {
            override fun onResponse(call: Call<PosterResponseList>, response: Response<PosterResponseList>) {
                Log.i("Poster", "getPosterAll 성공"+ response.body().toString())

                postList.clear() // 비우기
                response.body()?.posterResponseList?.let { it -> postList.addAll(it) }

                // 어댑터 연결 - 마감임박 카드 뷰
                adapter = PosterAdapter(postList, mContext)
                recyclerView!!.setAdapter(adapter)



            }

            override fun onFailure(call: Call<PosterResponseList>, t: Throwable) {
                Log.e("Poster", "getPosterAll 실패"+t.message.toString())
            }
        })
    }

}