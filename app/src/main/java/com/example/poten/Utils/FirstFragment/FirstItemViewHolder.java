package com.example.poten.Utils.FirstFragment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poten.Model.firstItem;
import com.example.poten.R;
import com.example.poten.Utils.SquareImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirstItemViewHolder extends RecyclerView.ViewHolder{

    public TextView tvTag;
    public TextView tvClubname;
    public TextView tvSubtitle;
    public TextView tvDday;

    public CircleImageView imgProfile;
    public SquareImageView imgPost;

    public FirstItemViewHolder(View itemView) {
        super(itemView);

        itemView.setClickable(true);
        tvTag = (TextView) itemView.findViewById(R.id.tag);
        tvClubname = (TextView) itemView.findViewById(R.id.clubname);
        tvSubtitle = (TextView) itemView.findViewById(R.id.subtitle);
        tvDday = (TextView) itemView.findViewById(R.id.dday);

        imgProfile = (CircleImageView) itemView.findViewById(R.id.profile_image);
        imgPost = (SquareImageView) itemView.findViewById(R.id.post_image);
    }

    public void bind(firstItem FirstItem){
        tvTag.setText(FirstItem.getTag());
        tvClubname.setText(FirstItem.getId());
        tvSubtitle.setText(FirstItem.getSubtitle());
        tvDday.setText(FirstItem.getDday());

        imgProfile.setImageResource(FirstItem.getImgProfile());
        imgPost.setImageResource(FirstItem.getImgNotice());
    }
}
