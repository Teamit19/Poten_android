package com.example.poten.Utils.SecondFragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poten.Model.secondItem;
import com.example.poten.R;

public class SecondItemViewHolder extends RecyclerView.ViewHolder{

    public TextView tvHeartCount;
    public TextView tvCommentCount;
    public TextView tvUsername;
    public TextView tvSubtitle;
    public ImageView imgBackground;
    public ImageView imgHeart;

    public SecondItemViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setClickable(true);
        tvHeartCount = (TextView) itemView.findViewById(R.id.heart_count);
        tvCommentCount = (TextView) itemView.findViewById(R.id.speech_count);
        tvUsername =  (TextView) itemView.findViewById(R.id.usernmae);
        tvSubtitle = (TextView) itemView.findViewById(R.id.subtitle);

        imgBackground = (ImageView) itemView.findViewById(R.id.imgBackground);
        imgHeart = (ImageView) itemView.findViewById(R.id.image_heart);
    }

    public void bind(secondItem SecondItem){
        Log.d("TAG", ""+SecondItem.getId());
        tvHeartCount.setText("" + SecondItem.getLikeCount());
        tvCommentCount.setText("" + SecondItem.getCommentCount());
        tvUsername.setText(SecondItem.getId());
        tvSubtitle.setText(SecondItem.getSubTitle());

        imgBackground.setImageResource(SecondItem.getImage());
    }
}
