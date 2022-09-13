package com.example.poten.Utils.SecondFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Model.secondItem
import com.example.poten.R


class SecondFragment : Fragment(){
    private lateinit var secondItemAdapter: SecondItemAdapter
    private val mContext: SecondFragment = this@SecondFragment
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: SecondItemAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    private val mMyData: ArrayList<secondItem>? = null

    private var lstContact: java.util.ArrayList<secondItem>? = null
    private lateinit var v : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_second, container, false)
        mRecyclerView = v.findViewById(R.id.recyclerView)
        val recyclerViewAdapter = SecondItemAdapter(context, lstContact)
        mRecyclerView?.setLayoutManager(LinearLayoutManager(activity))
        mRecyclerView?.setAdapter(recyclerViewAdapter)
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lstContact = java.util.ArrayList()
        lstContact!!.add(secondItem("아이디", R.drawable.ic_heart_red, 0, 1, "서브타이블"))
        lstContact!!.add(secondItem("아이디", R.drawable.ic_heart_red, 0, 1, "서브타이블"))
        lstContact!!.add(secondItem("아이디", R.drawable.ic_heart_red, 0, 1, "서브타이블"))
        lstContact!!.add(secondItem("아이디", R.drawable.ic_heart_red, 0, 1, "서브타이블"))

    }
    /*
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_second, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list: ArrayList<secondItem> = requireActivity().intent!!.extras!!.get("DataList") as ArrayList<secondItem>
        secondItemAdapter = SecondItemAdapter(list)

        var listview = view.findViewById(R.id.recyclerView) as RecyclerView
        listview.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        listview.adapter = secondItemAdapter
    }
    */
    /*
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

     */

}