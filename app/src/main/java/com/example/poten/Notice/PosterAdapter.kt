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
import com.example.poten.Utils.SquareImageView
import de.hdodenhof.circleimageview.CircleImageView

class PosterAdapter(private val context: Context) : RecyclerView.Adapter<PosterAdapter.ViewHolder>() {

    var datas= mutableListOf<PosterResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.layout_card_notice, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.clubname.text=datas[position].club?.clubName
        holder.subtitle.text=datas[position].content
        holder.dday.text="D-"+datas[position].dday
        holder.tag.text="#"+datas[position].club?.field
        holder.tag2.text="#"+datas[position].club?.activityType
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
        val clubname=itemView.findViewById<TextView>(R.id.clubname)
        val subtitle=itemView.findViewById<TextView>(R.id.subtitle)
        val dday=itemView.findViewById<TextView>(R.id.dday)
        val tag=itemView.findViewById<TextView>(R.id.tag)
        val tag2=itemView.findViewById<TextView>(R.id.tag2)
//        val profile_image=itemView.findViewById<CircleImageView>(R.id.profile_image)
//        val post_image=itemView.findViewById<SquareImageView>(R.id.post_image)


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