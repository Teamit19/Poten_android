package com.example.poten.Utils.FirstFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Model.firstItem
import com.example.poten.R
import java.util.ArrayList

class FirstFragment  : Fragment(){

    private var mRecyclerView: RecyclerView? = null
    private var lstItem : ArrayList<firstItem>? = null
    private lateinit var v : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_first, container, false)
        mRecyclerView = v.findViewById(R.id.recyclerView)
        val recyclerViewAdapter = FirstItemAdapter(context, lstItem)

        mRecyclerView?.layoutManager=LinearLayoutManager(activity)
        mRecyclerView?.adapter = recyclerViewAdapter

        return v

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lstItem = java.util.ArrayList()
        lstItem!!.add(firstItem("클럽 이름", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white))

    }
}