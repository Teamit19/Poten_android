package com.example.poten.Search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Board.model.PopularClubResponse
import com.example.poten.R

class PopularClubFragment1 : Fragment() {
    private lateinit var mContext: Context
    private var recyclerView: RecyclerView? = null
    private lateinit var adapter: PopularClubViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_popular_club, container, false)
        recyclerView = v.findViewById(R.id.recyclerview) as RecyclerView

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
        var postList = ArrayList<PopularClubResponse>()



        postList.add(PopularClubResponse("한사랑클라이밍회 부원 모집하는중인...", "한사랑 클라이밍회", "#서울 #오프라인", "D-6", "profile1", "post1"))
        postList.add(PopularClubResponse("카메라를 사랑하는 모임 12기 부원 모...", "카사모", "#경기도 #오프라인", "D-23", "profile2", "post2"))
        postList.add(PopularClubResponse("주식공부 여기서회 8기 부원 모집중입...", "주식공부 여기서회", "#서울 #온라인", "D-23", "profile3", "post3"))
        postList.add(PopularClubResponse("한사랑클라이밍회 부원 모집하는중인...", "한사랑 클라이밍회", "#서울 #오프라인", "D-6", "profile1", "post1"))


        adapter = PopularClubViewAdapter(postList, mContext)
        recyclerView!!.setAdapter(adapter)


    }
}