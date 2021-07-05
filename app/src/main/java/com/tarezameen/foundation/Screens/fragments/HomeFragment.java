package com.tarezameen.foundation.Screens.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tarezameen.foundation.R;
import com.tarezameen.foundation.Screens.Adapters.DemoRecyclerViewAdapter;
import com.tarezameen.foundation.Screens.Adapters.ImageAdapter;
import com.tarezameen.foundation.Screens.Models.ImageList;

import java.util.ArrayList;

import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ScrollingPagerIndicator indicator;
    ImageAdapter mAdapter;
    ArrayList imageList = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container,
                false);

        setHasOptionsMenu(true);
        return rootView;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Home");

        recyclerView = view.findViewById(R.id.recyclerView);

        for (int i = 0; i < 3; i++) {
            ImageList list = new ImageList();
            String imageurl = "https://homepages.cae.wisc.edu/~ece533/images/airplane.png";
            list.setImageurl(imageurl);
            imageList.add(list);

        }

        indicator = (ScrollingPagerIndicator) view.findViewById(R.id.indicator);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new ImageAdapter(imageList, getActivity().getApplicationContext());
        recyclerView.setAdapter(mAdapter);
        indicator.attachToRecyclerView(recyclerView);
    }
}