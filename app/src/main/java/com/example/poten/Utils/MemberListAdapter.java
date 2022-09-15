package com.example.poten.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.poten.Model.memberList;
import com.example.poten.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MyViewHolder> {

    private Context mContext;
    private List<memberList> albumList;

    int[] myImageList = {R.drawable.ic_account_circle};

    public MemberListAdapter(Context mContext, List<memberList> albumList){
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_memberlist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        memberList memberList = albumList.get(position);
        holder.tvTitle.setText(memberList.getId());
        holder.imgProfile.setImageResource(memberList.getImage());

        // 부장 마커 표시
        if(position == 0){
            holder.imgLeaderitem.setVisibility(View.VISIBLE);
        }
        
        holder.layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public CircleImageView imgProfile, imgLeaderitem;
        ConstraintLayout layout;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tvId);
            imgProfile = (CircleImageView) view.findViewById(R.id.imgitem);
            imgLeaderitem = (CircleImageView) view.findViewById(R.id.imgLeaderitem);
            layout = (ConstraintLayout) view.findViewById(R.id.rlmain);
        }
    }
}
