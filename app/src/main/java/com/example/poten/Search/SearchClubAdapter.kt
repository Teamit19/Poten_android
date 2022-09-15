package com.example.poten.Search

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.poten.Board.model.ClubResponse
import com.example.poten.Board.model.PopularClubResponse
import com.example.poten.Board.model.SearchClubResponse
import com.example.poten.MyPage.ClubMyPageActivity
import com.example.poten.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class SearchClubAdapter(var clubList : ArrayList<ClubResponse>, context: Context) : RecyclerView.Adapter <SearchClubAdapter.CustomViewHolder>() {
    private var c =  context
    private var follow_cnt = 0
    private var cnt: MutableList<Int> = MutableList(clubList.size){0}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchClubAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_search_club_listitem, parent,false)
        return SearchClubAdapter.CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchClubAdapter.CustomViewHolder, position: Int) {
        // 데이터 연결
        holder.clubname.text = clubList[position].clubName
        holder.tag.text= "#"+clubList[position].region+" #"+clubList[position].activityType
        holder.content.text = clubList[position].clubDesc
//        var p1 = c.resources.getIdentifier(clubList[position].profile?.fileName,"drawable", c.packageName)
//        var p1 = c.resources.getIdentifier("profile1","drawable", c.packageName)

        Picasso.get()
            .load("http://172.30.1.3:8080/files/images/"+ clubList[position].profile?.fileName)
            .into(holder.profile_photo);
        // 게시물 사진 연결
//        Picasso.get().load(p1).into(holder.profile_photo);


        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = 500
        holder.itemView.requestLayout()

        holder.follow_btn.setOnClickListener(View.OnClickListener {
            println("follow")
            if(cnt.get(position) == 0){
                holder.follow_btn.setTextColor(Color.GRAY)
                holder.follow_btn.setBackgroundResource(R.drawable.rectangle_174)
                cnt[position] = 1
            } else {
                holder.follow_btn.setTextColor(Color.WHITE)
                holder.follow_btn.setBackgroundResource(R.drawable.rectangle_177)
                cnt[position] = 0
            }


        })

        holder.more_btn.setOnClickListener(View.OnClickListener {
            println("more")
            val intent1 = Intent(it.context, ClubMyPageActivity::class.java) //ACTIVITY_NUM = 0
            intent1.putExtra("clubId", clubList[position].clubId); /*송신*/

            it.context.startActivity(intent1)



        })
    }

    override fun getItemCount(): Int {
//        Log.i("BOARD", "getItemCount" + clubList.size)
        return clubList.size
    }

    class CustomViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        val clubname = itemView.findViewById<TextView>(R.id.clubname) //동아리 이름
        val tag = itemView.findViewById<TextView>(R.id.tag)
        val profile_photo = itemView.findViewById<CircleImageView>(R.id.profile_image)
        val content = itemView.findViewById<TextView>(R.id.subtitle)
        val follow_btn = itemView.findViewById<TextView>(R.id.follow)
        val more_btn = itemView.findViewById<TextView>(R.id.more)


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
