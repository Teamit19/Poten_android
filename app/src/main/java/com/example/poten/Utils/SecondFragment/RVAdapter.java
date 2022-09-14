package com.example.poten.Utils.SecondFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poten.Model.firstItem;
import com.example.poten.Model.secondItem;
import com.example.poten.R;
import com.example.poten.Utils.SecondFragment.SecondItemViewHolder;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<SecondItemViewHolder> {

    private List<secondItem> mSecondItem;

    public RVAdapter(List<secondItem> mSecondItem){
        this.mSecondItem = mSecondItem;
    }

    @Override
    public SecondItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activitylist, parent, false);
        return new SecondItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SecondItemViewHolder holder, int position) {
        final secondItem item = mSecondItem.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() { return mSecondItem.size(); }
}