package com.example.poten.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.R
import com.example.poten.Utils.SquareImageView
import de.hdodenhof.circleimageview.CircleImageView

class PostListViewAdapter(private val context: Context) : RecyclerView.Adapter <PostListViewAdapter.CustomViewHolder>() {
    private var postList =  mutableListOf<Post>()

    fun setListData(data:MutableList<Post>){
        postList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_view_post, parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostListViewAdapter.CustomViewHolder, position: Int) {
//        TODO("아이템과 백엔드 데이터 연결")
        holder.clubname.text="cookingTeens"
        holder.username.text="test"
        holder.image_time_posted.text="1분전"

        holder.heart_count.text = "1"
        holder.speech_count.text = "5"
        holder.image_caption.text = "쿠킹틴즈 입니다~!~!"

    }

    override fun getItemCount(): Int {
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
    }



}
