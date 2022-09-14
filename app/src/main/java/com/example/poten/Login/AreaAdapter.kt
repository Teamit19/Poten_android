package com.example.poten.Login

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.R
import com.example.poten.databinding.ItemCategoryBinding


class AreaAdapter(private val context: Context) : RecyclerView.Adapter<AreaAdapter.ViewHolder>() {

    var datas = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
//        val binding=ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_item_name.text=datas[position]
        holder.tv_item_name.setOnClickListener {
            itemClickListener.onClick(it, position)
            it.setBackgroundResource(R.drawable.round_click)
            holder.tv_item_name.setTextColor(Color.WHITE)
        }
//        holder.bind(datas[position])
//        holder.itemView.setOnClickListener {
//            itemClickListener.onClick(it, position)
//        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_item_name=itemView.findViewById<AppCompatButton>(R.id.tv_item_name)


//        fun bind(item: String) {
//            binding.tvItemName.text=item
//
//        }


    }

    // (2) 리스너 인터페이스
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