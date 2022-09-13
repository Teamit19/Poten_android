package com.example.poten.Utils.FirstFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poten.Model.SearchNoticeItem;
import com.example.poten.Model.firstItem;
import com.example.poten.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FirstFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<firstItem> mFirstItem;
    private RVAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstaceState){ super.onCreate(savedInstaceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManger = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManger);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
        mFirstItem = new ArrayList<>();

        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));
        mFirstItem.add(new firstItem("클럽명", "태그", "서브타이틀", "디데이", R.drawable.ic_heart_red, R.drawable.ic_heart_white));

        adapter = new com.example.poten.Utils.FirstFragment.RVAdapter(mFirstItem);
        recyclerView.setAdapter(adapter);

        Log.d("TAG", "어댑터 연결 완료");


    }

}
