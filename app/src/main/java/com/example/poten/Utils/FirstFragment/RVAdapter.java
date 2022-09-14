package com.example.poten.Utils.FirstFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poten.Model.firstItem;
import com.example.poten.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<FirstItemViewHolder> {

    private List<firstItem> mFirstItem;

    public RVAdapter(List<firstItem> mFirstItem){
        this.mFirstItem = mFirstItem;
    }

    @Override
    public FirstItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noticelist, parent, false);
        return new FirstItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstItemViewHolder holder, int position) {
        final firstItem item = mFirstItem.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() { return mFirstItem.size(); }
}
