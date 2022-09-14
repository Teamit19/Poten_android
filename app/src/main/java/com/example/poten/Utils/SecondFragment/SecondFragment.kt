package com.example.poten.Utils.SecondFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Board.model.BoardResponse
import com.example.poten.Board.model.BoardResponseList
import com.example.poten.Model.secondItem
import com.example.poten.R
import com.example.poten.Utils.RetrofitClient
import com.example.poten.interfaces.BoardApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class SecondFragment() : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var mSecondItem: List<secondItem>
    lateinit var adapter: RVAdapter
    private var clubId by Delegates.notNull<Long>()

    var retrofit = RetrofitClient.create(BoardApi::class.java)
    private val postList =  mutableListOf<BoardResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 파라미터 불러오기
        arguments?.let {
            clubId = it.getLong("clubId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_second, container, false)

        recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        val layoutManger: LinearLayoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layoutManger

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 데이터 불러오기
        getBoardAllByClub(clubId)

        Log.d("TAG", "활동 어댑터 연결 완료")

    }

    private fun getBoardAllByClub(clubId: Long) {
        Log.i("BOARD", "getBoardAllByClub 호출됨")
        var retrofit = RetrofitClient.create(BoardApi::class.java, RetrofitClient.getAuth())

        retrofit.getBoardByClub(clubId).enqueue(object : Callback<BoardResponseList> {
            override fun onResponse(call: Call<BoardResponseList>, response: Response<BoardResponseList>) {
                Log.i("BOARD", "getBoardAllByClub 성공"+ response.body().toString())

                postList.clear() // 비우기
                response.body()?.boardResponseList?.let { it -> postList.addAll(it) }

                // 데이터 셋팅
                setHasOptionsMenu(true)
                mSecondItem = ArrayList()

                for (post in postList){
                    (mSecondItem as ArrayList<secondItem>).add(secondItem(post.writer?.name, post.pics?.fileName, post.heartsNum!!.toInt(), post.commentsNum!!.toInt(), post.content))
                }

                adapter = RVAdapter(mSecondItem)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<BoardResponseList>, t: Throwable) {
                Log.e("BOARD", "getBoardAllByClub 실패"+t.message.toString())
            }
        })
    }
}