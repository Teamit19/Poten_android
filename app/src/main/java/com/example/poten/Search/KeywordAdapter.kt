package com.example.poten.Search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.databinding.ItemPopularKeywordBinding

class KeywordAdapter(
    private val context: Context
): RecyclerView.Adapter<KeywordAdapter.SelectSingleItemViewHolder>() {

    var list= mutableListOf<String>()

    private lateinit var binding: ItemPopularKeywordBinding
    private var onItemClickListener: OnItemClickListener? = null


    interface OnItemClickListener {
        fun onClick(v: View, position: Int){
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    inner class SelectSingleItemViewHolder(
        private val binding: ItemPopularKeywordBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: View) {
            binding.tvItemName.text = list[adapterPosition]

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectSingleItemViewHolder {
        binding = ItemPopularKeywordBinding.inflate(LayoutInflater.from(context), parent, false)
        return SelectSingleItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectSingleItemViewHolder, position: Int) {
        holder.bind(holder.itemView)
    }

    override fun getItemCount(): Int = list.size




}
