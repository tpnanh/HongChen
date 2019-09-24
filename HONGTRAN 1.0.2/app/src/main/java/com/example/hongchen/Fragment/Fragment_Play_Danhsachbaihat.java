package com.example.hongchen.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hongchen.Activity.PlayActivity;
import com.example.hongchen.Adapter.PlayAdapter;
import com.example.hongchen.R;

public class Fragment_Play_Danhsachbaihat extends Fragment {
    View view;
    RecyclerView recyclerviewplaydanhsachbaihat;
    PlayAdapter playAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danhsachbaihat,container,false);
        recyclerviewplaydanhsachbaihat = view.findViewById(R.id.recyclerviewplaydanhsachbaihat);
        if (PlayActivity.baihatArrayList.size() > 0) {
            playAdapter = new PlayAdapter(getActivity(), PlayActivity.baihatArrayList);
            recyclerviewplaydanhsachbaihat.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerviewplaydanhsachbaihat.setAdapter(playAdapter);
        }

        return view;
    }
}
