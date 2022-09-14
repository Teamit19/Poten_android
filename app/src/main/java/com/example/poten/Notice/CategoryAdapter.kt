package com.example.poten.Notice

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Login.AreaAdapter
import com.example.poten.R

class CategoryAdapter (private val context: Context) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    var datas = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
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
        }    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_item_name=itemView.findViewById<AppCompatButton>(R.id.tv_item_name)


    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int){
        }
    }

    fun setItemClickListener(onItemClickListener: AreaAdapter.OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : AreaAdapter.OnItemClickListener



}