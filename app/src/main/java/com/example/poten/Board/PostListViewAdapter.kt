package com.example.poten.Board

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.poten.Board.model.BoardResponse
import com.example.poten.R
import com.example.poten.Utils.SquareImageView
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

//        Glide.with(this@PostListViewAdapter).load("http://172.30.1.3:8080/files/images/"+ postList[position].picUrl.toString()
        Log.e( "BOARD", postList[position].pics?.fileName.toString());
        Glide.with(holder.itemView?.context)
            .load("http://172.30.1.3:8080/files/images/"+ postList[position].pics?.fileName)
            .into(holder.post_images)


        // 하트 연결
//        holder.image_heart.setOnClickListener(
////            onHeartClicked()
//        )
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
        val post_images = itemView.findViewById<SquareImageView>(R.id.post_images)

        val heart_count = itemView.findViewById<TextView>(R.id.heart_count)
        val speech_count = itemView.findViewById<TextView>(R.id.speech_count)
        val image_caption = itemView.findViewById<TextView>(R.id.image_caption)

        val image_heart = itemView.findViewById<ImageView>(R.id.image_heart)
        val speech_bubble = itemView.findViewById<ImageView>(R.id.speech_bubble)
    }



}
