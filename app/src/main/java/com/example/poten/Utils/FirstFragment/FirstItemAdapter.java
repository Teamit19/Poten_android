package com.example.poten.Utils.FirstFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poten.Model.firstItem;
import com.example.poten.R;

import java.util.List;

public class FirstItemAdapter extends RecyclerView.Adapter<FirstItemAdapter.MyViewHolder> {

    private Context mContext;
    private List<firstItem> itemList;

    public FirstItemAdapter(Context mContext, List<firstItem> itemList){
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_noticelist, parent, false);

        return new FirstItemAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        firstItem item = itemList.get(position);
        holder.tvTag.setText(item.getTag());
        holder.tvClubname.setText(item.getId());
        holder.tvSubtitle.setText(item.getSubtitle());
        holder.tvDday.setText(item.getDday());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTag, tvClubname, tvDday, tvSubtitle;
        public ImageView imgProfile, imgPost;

        public MyViewHolder(View view){
            super(view);
            tvTag = view.findViewById(R.id.tag);
            tvClubname = view.findViewById(R.id.clubname);
            tvDday = view.findViewById(R.id.dday);
            tvSubtitle = view.findViewById(R.id.subtitle);

            imgProfile = view.findViewById(R.id.profile_image);
            imgPost = view.findViewById(R.id.post_image);
        }
    }
}

