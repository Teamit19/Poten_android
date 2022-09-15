package com.example.poten.Search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Board.model.PopularClubResponse
import com.example.poten.R

class SearchClubAdapter(var postList : ArrayList<PopularClubResponse>, context: Context) : RecyclerView.Adapter <PopularClubViewAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularClubViewAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_popular_club_listitem, parent,false)
        return PopularClubViewAdapter.CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularClubViewAdapter.CustomViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}