package com.example.poten.Utils.SecondFragment

import android.content.Context
import com.example.poten.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Model.secondItem
import com.example.poten.databinding.FragmentFirstBinding
import com.example.poten.databinding.FragmentSecondBinding


class SecondFragment : Fragment(){
    private val mContext: SecondFragment = this@SecondFragment
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: SecondItemAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private val mMyData: ArrayList<secondItem>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        /*
        val view: View = inflater.inflate(R.layout.item_activitylist, container, false)
        mRecyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView

        mLayoutManager = LinearLayoutManager(activity)
        mRecyclerView?.setLayoutManager(mLayoutManager)

        mAdapter = SecondItemAdapter(mMyData)

        mRecyclerView?.setAdapter(mAdapter)
        mRecyclerView?.setItemAnimator(DefaultItemAnimator())
        return view

         */
        return FragmentSecondBinding.inflate(
            inflater,
            container,
            false
        ).apply {

        }.root
    }

}