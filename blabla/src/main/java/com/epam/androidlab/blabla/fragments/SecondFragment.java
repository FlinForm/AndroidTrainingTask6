package com.epam.androidlab.blabla.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.epam.androidlab.blabla.R;
import com.epam.androidlab.blabla.RecycleViewAdapter;
import com.epam.androidlab.blabla.ViewItems;
import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    private List<ViewItems> items;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initList();

        RecycleViewAdapter adapter = new RecycleViewAdapter(items);

        View view = inflater.inflate(R.layout.second_fragment, null);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && fab.isShown()) {
                    fab.hide();
                }
                if (dy < 0 && !fab.isShown()) {
                    fab.show();
                }
            }
        });

        return view;
    }

    private void initList() {
        items = new ArrayList<>();
        items.add(new ViewItems("Johnny Cash", "Out among the stars", R.drawable.johnnycash));
        items.add(new ViewItems("Pink Floyd", "The dark side of the moon", R.drawable.pinkfloyd));
        items.add(new ViewItems("Rolling Stones", "Beggars Banquet", R.drawable.rollingstones));
        items.add(new ViewItems("Beatles", "Abbey Road", R.drawable.beatles));
        items.add(new ViewItems("The Doors", "The doors", R.drawable.thedoors));
        items.add(new ViewItems("AC/DC", "T.N.T.", R.drawable.acdc));
        items.add(new ViewItems("Jimi Hendrix", "Are you experienced", R.drawable.hendrix));
        items.add(new ViewItems("Deep Purple", "Deep Purple In Rock", R.drawable.deeppurple));
        items.add(new ViewItems("Led Zeppelin", "Led Zeppelin I", R.drawable.ledzeppelin));
        items.add(new ViewItems("David Bowie", "The man who sold the world", R.drawable.bowie));
    }
}
