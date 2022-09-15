package com.example.poten.MyPage

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
import com.example.poten.Search.PosterAdapter
import com.example.poten.Utils.RetrofitClient
import com.example.poten.interfaces.ClubApi
import com.example.poten.interfaces.PosterApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class ClubPosterFragment : Fragment() {
    private lateinit var mContext: Context
    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PosterAdapter
    private val postList =  ArrayList<PosterResponse>()
    private var clubId by Delegates.notNull<Long>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_club_poster, container, false)

        arguments?.let {
            clubId = it.getLong("clubId")
        }

        recyclerView = v.findViewById<View>(R.id.recyclerView) as RecyclerView

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
        getPoster()


    }


    private fun getPoster() {
        var retrofit = RetrofitClient.create(PosterApi::class.java, RetrofitClient.getAuth())

        retrofit.getPosterByClub(clubId).enqueue(object : Callback<PosterResponseList> {
            override fun onResponse(call: Call<PosterResponseList>, response: Response<PosterResponseList>) {
                Log.i("Poster", "getPosterByClub 성공"+ response.body().toString())

                postList.clear() // 비우기
                response.body()?.posterResponseList?.let { it -> postList.addAll(it) }

                // 어댑터 연결 - 마감임박 카드 뷰
                adapter = PosterAdapter(postList, mContext)
                recyclerView.adapter=adapter
//                recyclerView!!.setAdapter(adapter)



            }

            override fun onFailure(call: Call<PosterResponseList>, t: Throwable) {
                Log.e("Poster", "getPosterByClub 실패"+t.message.toString())
            }
        })
    }


}