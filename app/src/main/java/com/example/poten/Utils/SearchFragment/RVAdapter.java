package com.example.poten.Utils.SearchFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poten.Model.SearchNoticeItem;
import com.example.poten.R;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<SearchNoticeItem> mSearchNoticeItem;
    private List<SearchNoticeItem> mOriginalNoticeItem;

    public RVAdapter(List<SearchNoticeItem> mSearchNoticeItem){
        this.mSearchNoticeItem = mSearchNoticeItem;
        this.mOriginalNoticeItem = mSearchNoticeItem;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_search, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        final SearchNoticeItem item = mSearchNoticeItem.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() { return mSearchNoticeItem.size(); }

    public void setFilter(List<SearchNoticeItem> searchNoticeItems){
        mSearchNoticeItem = new ArrayList<>();
        mSearchNoticeItem.addAll(searchNoticeItems);
        notifyDataSetChanged();
    }
}
