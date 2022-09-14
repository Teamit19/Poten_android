package com.example.poten.Notice

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Board.model.BoardResponse
import com.example.poten.Board.model.PosterResponse
import com.example.poten.R

class CardAdapter(private val context: Context) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    var datas= mutableListOf<PosterResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.layout_card_notice, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_title.text=datas[position].club?.clubName
        holder.tv_subtitle.text=datas[position].content
        holder.tv_dday.text="D-"+datas[position].deadlineDate
        holder.tv_hashtag.text=datas[position].club?.field
        holder.tv_hashtag2.text=datas[position].club?.activityType
//        holder.relLayout1.setOnClickListener {
//
//        }
    //        holder.tv_item_name.setOnClickListener {
//            itemClickListener.onClick(it, position)
//            it.setBackgroundResource(R.drawable.round_click)
//            holder.tv_item_name.setTextColor(Color.WHITE)
//        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_title=itemView.findViewById<TextView>(R.id.tv_title)
        val tv_subtitle=itemView.findViewById<TextView>(R.id.tv_subtitle)
        val tv_dday=itemView.findViewById<TextView>(R.id.tv_dday)
        val tv_hashtag=itemView.findViewById<TextView>(R.id.tv_hashtag)
        val tv_hashtag2=itemView.findViewById<TextView>(R.id.tv_hashtag2)
        val relLayout1=itemView.findViewById<RelativeLayout>(R.id.relLayout1)


    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int){
        }
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener


}