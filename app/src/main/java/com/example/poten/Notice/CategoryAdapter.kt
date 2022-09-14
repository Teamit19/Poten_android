//package com.example.poten.Notice
//
//import android.content.Context
//import android.graphics.Color
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.appcompat.widget.AppCompatButton
//import androidx.recyclerview.widget.RecyclerView
//import com.example.poten.Login.AreaAdapter
//import com.example.poten.R
//
//class CategoryAdapter (private val context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
//
//    var datas = mutableListOf<String>()
//    var selectedPosition = 0
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
//        return ViewHolder(view)
//
//    }
//
//    override fun getItemCount(): Int {
//        return datas.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.tv_item_name.text=datas[position]
//
//        if (position == selectedPosition) {
//            holder.tv_item_name.setBackgroundResource(R.drawable.round_click)
//            holder.tv_item_name.setTextColor(Color.WHITE)
//        } else {
//            holder.tv_item_name.setBackgroundResource(R.drawable.round)
//            holder.tv_item_name.setTextColor(Color.parseColor("#525252"))
//        }
//
//        holder.tv_item_name.setOnClickListener {
//            var currentPosition = holder.adapterPosition
//            if (selectedPosition == currentPosition) {
//                //이미 아이템이 선택된 상태
//                selectedPosition = 0
//                holder.tv_item_name.setBackgroundResource(R.drawable.round)
//                holder.tv_item_name.setTextColor(Color.parseColor("#525252"))
//            } else {
//                selectedPosition=currentPosition
//                holder.tv_item_name.setBackgroundResource(R.drawable.round_click)
//                holder.tv_item_name.setTextColor(Color.WHITE)
//            }
//
////            itemClickListener.onClick(it, position)
////            it.setBackgroundResource(R.drawable.round_click)
////            holder.tv_item_name.setTextColor(Color.WHITE)
//        }
//    }
//
//
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val tv_item_name=itemView.findViewById<AppCompatButton>(R.id.tv_item_name)
//
//        fun bind() {
//            if (selectedPosition==) {
//                holder.tv_item_name.setBackgroundResource(R.drawable.round_click)
//                holder.tv_item_name.setTextColor(Color.WHITE)
//            } else {
//                holder.tv_item_name.setBackgroundResource(R.drawable.round)
//                holder.tv_item_name.setTextColor(Color.parseColor("#525252"))
//            }
//        }
//
//
//
//
//    }
//
//    interface OnItemClickListener {
//        fun onClick(v: View, position: Int){
//        }
//    }
//
//    fun setItemClickListener(onItemClickListener: AreaAdapter.OnItemClickListener) {
//        this.itemClickListener = onItemClickListener
//    }
//
//    private lateinit var itemClickListener : AreaAdapter.OnItemClickListener
//
//
//
//}