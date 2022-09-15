package com.example.poten.Utils.SecondFragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.poten.Model.secondItem;
import com.example.poten.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class SecondItemAdapter extends RecyclerView.Adapter<SecondItemAdapter.MyViewHolder> {

    private Context mContext;
    private List<secondItem> albumList;

    int[] myImageList = {R.drawable.ic_account_circle};

    public SecondItemAdapter(){}
    public SecondItemAdapter(List<secondItem> albumList){
        this.albumList = albumList;
    }
    public SecondItemAdapter(Context mContext, List<secondItem> albumList){
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public SecondItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_activitylist, parent, false);

        return new SecondItemAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        secondItem itemList = albumList.get(position);
        holder.tvheart_count.setText(itemList.getLikeCount());
        holder.tvcomment_count.setText(itemList.getCommentCount());
        holder.tvusername.setText(itemList.getId());
        holder.tvsubtitle.setText(itemList.getSubTitle());

        // 게시물 사진 연결
        Picasso.get()
                .load("http://172.30.1.3:8080/files/images/"+ itemList.getFileName())
            .into(holder.imgBackground);

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
        public TextView tvheart_count, tvcomment_count, tvusername, tvsubtitle;
        public ImageView imgHeart, imgBackground;
        RelativeLayout layout;

        public MyViewHolder(View view) {
            super(view);
            imgBackground = (ImageView) view.findViewById(R.id.imgBackground);
            tvheart_count = (TextView) view.findViewById(R.id.heart_count);
            tvcomment_count = (TextView) view.findViewById(R.id.speech_count);
            tvusername = (TextView) view.findViewById(R.id.username);
            tvsubtitle = (TextView) view.findViewById(R.id.subtitle);
            imgHeart = (ImageView) view.findViewById(R.id.image_heart);
            layout = (RelativeLayout) view.findViewById(R.id.rlmain);
        }
    }
}
