package com.example.poten.Utils.SearchFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.poten.Model.SearchNoticeItem;
import com.example.poten.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClubFragment extends Fragment implements SearchView.OnQueryTextListener {

private RecyclerView recyclerView;
private List<SearchNoticeItem> mNoticeItem;
private SearchView searchView;
private RVAdapter adapter;

@Override
public void onCreate(Bundle savedInstaceState){ super.onCreate(savedInstaceState);}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){
        View view = inflater.inflate(R.layout.search_club_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManger = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManger);

        return view;
        }

@Override
public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
        String[] locales = Locale.getISOCountries();
        mNoticeItem = new ArrayList<>();

        for (String countryCode : locales) {
        Locale obj = new Locale("", countryCode);
        mNoticeItem.add(new SearchNoticeItem(obj.getDisplayCountry(), obj.getISO3Country()));
        }

        adapter = new RVAdapter(mNoticeItem);
        recyclerView.setAdapter(adapter);
}

@Override
public boolean onQueryTextSubmit(String s) {
final List<SearchNoticeItem> filteredItemList = filter(mNoticeItem, s);
        adapter.setFilter(filteredItemList);
        return true;
        }

@Override
public boolean onQueryTextChange(String s) { return false; }

private List<SearchNoticeItem> filter(List<SearchNoticeItem> items, String query){
        query = query.toLowerCase();

final List<SearchNoticeItem> filteredItemList = new ArrayList<>();
        for (SearchNoticeItem item : items){
final String text = item.getName().toLowerCase();

        if (text.contains(query)){
        filteredItemList.add(item);
        }
        }

        return filteredItemList;
        }
        }
