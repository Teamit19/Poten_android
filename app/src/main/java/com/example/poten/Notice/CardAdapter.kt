package com.example.poten.Notice

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Board.model.BoardResponse
import com.example.poten.R

class CardAdapter(private val context: Context) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    var datas= mutableListOf<BoardResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.tv_item_name.text=datas[position]
//        holder.tv_item_name.setOnClickListener {
//            itemClickListener.onClick(it, position)
//            it.setBackgroundResource(R.drawable.round_click)
//            holder.tv_item_name.setTextColor(Color.WHITE)
//        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_item_name=itemView.findViewById<AppCompatButton>(R.id.tv_item_name)


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