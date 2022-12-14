package com.example.poten.Board

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.poten.Board.model.BoardResponse
import com.example.poten.Board.model.HeartBoardResponse
import com.example.poten.R
import com.example.poten.Utils.SquareImageView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class PostListViewAdapter(private val context: Context) : RecyclerView.Adapter <PostListViewAdapter.CustomViewHolder>() {
    private var postList =  mutableListOf<BoardResponse>()

    fun setListData(data:MutableList<BoardResponse>){
        postList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_view_post, parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostListViewAdapter.CustomViewHolder, position: Int) {
        // 데이터 연결
        holder.clubname.text = postList[position].club?.clubName
        holder.username.text= postList[position].writer?.name
        holder.image_time_posted.text = "1분전"

        holder.heart_count.text = postList[position].hearts?.size.toString()
        holder.speech_count.text = postList[position].comment?.size.toString()
        holder.image_caption.text = postList[position].content.toString()

        // 게시물 사진 연결
        Picasso.get()
            .load("http://172.30.1.3:8080/files/images/"+ postList[position].pics?.fileName)
            .into(holder.post_images);

        // 하트 연결
        holder.image_heart.setOnClickListener{
//            itemClickListener.onClick(it, position, postList[position])
            holder.image_heart.setColorFilter(Color.parseColor("#F32323"))
            holder.heart_count.text = (postList[position].hearts?.size?.plus(1)).toString()
        }

    }

    override fun getItemCount(): Int {
//        Log.i("BOARD", "getItemCount" + postList.size)
        return postList.size
    }

    class CustomViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val clubname = itemView.findViewById<TextView>(R.id.clubname)
        val username = itemView.findViewById<TextView>(R.id.username)
        val image_time_posted = itemView.findViewById<TextView>(R.id.image_time_posted)

        val profile_photo = itemView.findViewById<CircleImageView>(R.id.profile_photo)
        val post_images = itemView.findViewById<ImageView>(R.id.post_images)

        val heart_count = itemView.findViewById<TextView>(R.id.heart_count)
        val speech_count = itemView.findViewById<TextView>(R.id.speech_count)
        val image_caption = itemView.findViewById<TextView>(R.id.image_caption)

        val image_heart = itemView.findViewById<ImageView>(R.id.image_heart)
        val speech_bubble = itemView.findViewById<ImageView>(R.id.speech_bubble)
    }


    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int, b : BoardResponse){

        }
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener


}
