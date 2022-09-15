package com.example.poten.Search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Board.model.BoardResponse
import com.example.poten.Board.model.PopularClubResponse
import com.example.poten.Board.model.PosterResponse
import com.example.poten.Model.SearchNoticeItem
import com.example.poten.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class PosterAdapter(var postList : ArrayList<PosterResponse>, context: Context) : RecyclerView.Adapter <PosterAdapter.CustomViewHolder>() {

    private var c =  context

//    fun setListData(data:MutableList<BoardResponse>){
//        postList = data
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_popular_club_listitem, parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: PosterAdapter.CustomViewHolder, position: Int) {
        // 데이터 연결
        holder.clubname.text = postList[position].club?.clubName
        holder.tag.text="#"+postList[position].club?.field+" #"+postList[position].club?.activityType
//        holder.tag2.text="#"+postList[position].club?.activityType
        holder.dday.text="D-"+postList[position].dday
        holder.subtitle.text = postList[position].content

        // 게시물 사진 연결
        Picasso.get()
            .load("http://172.30.1.3:8080/files/images/"+ postList[position].posterImg?.fileName)
            .into(holder.post_images);

        // 동아리 프로필 연결
        Picasso.get()
            .load("http://172.30.1.3:8080/files/images/"+ postList[position].club?.profile?.fileName)
            .into(holder.profile_photo);
        

        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 250
        holder.itemView.requestLayout()

    }

    override fun getItemCount(): Int {
//        Log.i("BOARD", "getItemCount" + postList.size)
        return postList.size
    }

    class CustomViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

        val clubname=itemView.findViewById<TextView>(R.id.clubname)
        val subtitle=itemView.findViewById<TextView>(R.id.subtitle)
        val dday=itemView.findViewById<TextView>(R.id.dday)
        val tag=itemView.findViewById<TextView>(R.id.tag)
//        val tag2=itemView.findViewById<TextView>(R.id.tag2)
        val profile_photo = itemView.findViewById<CircleImageView>(R.id.profile_image)
        val post_images = itemView.findViewById<ImageView>(R.id.post_image)

    }


    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int, b : PosterResponse){

        }
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener


}
