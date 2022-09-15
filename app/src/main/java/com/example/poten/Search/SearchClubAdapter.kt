package com.example.poten.Search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Board.model.PopularClubResponse
import com.example.poten.Board.model.SearchClubResponse
import com.example.poten.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class SearchClubAdapter(var postList : ArrayList<PopularClubResponse>, context: Context) : RecyclerView.Adapter <SearchClubAdapter.CustomViewHolder>() {
    private var c =  context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchClubAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_search_club_listitem, parent,false)
        return SearchClubAdapter.CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchClubAdapter.CustomViewHolder, position: Int) {
        // 데이터 연결
        holder.clubname.text = postList[position].clubname
        holder.tag.text= postList[position].tag
        holder.dday.text = postList[position].dday
        holder.content.text = postList[position].content
        var p1 = c.resources.getIdentifier(postList[position].profile,"drawable", c.packageName)


        // 게시물 사진 연결
        Picasso.get().load(p1).into(holder.profile_photo);


        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 500
        holder.itemView.requestLayout()

    }

    override fun getItemCount(): Int {
//        Log.i("BOARD", "getItemCount" + postList.size)
        return postList.size
    }

    class CustomViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val clubname = itemView.findViewById<TextView>(R.id.clubname) //동아리 이름
        val tag = itemView.findViewById<TextView>(R.id.tag)
        val dday = itemView.findViewById<TextView>(R.id.dday)
        val profile_photo = itemView.findViewById<CircleImageView>(R.id.profile_image)
        val content = itemView.findViewById<TextView>(R.id.subtitle)


    }



    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int, b : PopularClubResponse){

        }
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
}