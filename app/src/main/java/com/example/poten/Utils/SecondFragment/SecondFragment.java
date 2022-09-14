package com.example.poten.Utils.SecondFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poten.Model.secondItem;
import com.example.poten.R;
import com.example.poten.Utils.SecondFragment.RVAdapter;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<secondItem> mSecondItem;
    private RVAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstaceState){ super.onCreate(savedInstaceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManger = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManger);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
        mSecondItem = new ArrayList<>();

        mSecondItem.add(new secondItem("제목", R.drawable.ic_heart_red, 500, 20000, "서브타이틀"));
        mSecondItem.add(new secondItem("제목", R.drawable.ic_heart_red, 500, 20000, "서브타이틀"));

        adapter = new com.example.poten.Utils.SecondFragment.RVAdapter(mSecondItem);
        recyclerView.setAdapter(adapter);

        Log.d("TAG", "활동 어댑터 연결 완료");

    }
}
