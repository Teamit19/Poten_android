package com.example.poten.Utils.SearchFragment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.poten.Model.SearchNoticeItem;
import com.example.poten.R;

public class ItemViewHolder extends RecyclerView.ViewHolder{

    public TextView tvName;
    public TextView tvId;

    public ItemViewHolder(View itemView){
        super(itemView);

        itemView.setClickable(true);
        tvName = (TextView) itemView.findViewById(R.id.country_name);
        tvId = (TextView) itemView.findViewById(R.id.country_id);
    }

    public void bind(SearchNoticeItem searchNoticeItem){
        tvName.setText(searchNoticeItem.getName());
        tvId.setText(searchNoticeItem.getId());
    }
}
