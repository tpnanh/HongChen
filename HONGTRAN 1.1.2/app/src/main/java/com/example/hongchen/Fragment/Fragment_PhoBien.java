package com.example.hongchen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hongchen.Activity.DanhsachcacplaylistActivity;
import com.example.hongchen.Adapter.PhobienAdapter;
import com.example.hongchen.Dialog.Dialog;
import com.example.hongchen.Model.Playlist;
import com.example.hongchen.R;
import com.example.hongchen.Service.APIService;
import com.example.hongchen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PhoBien extends Fragment {
    View view;
    RecyclerView recyclerviewphobien;
    TextView tvphobien;
    PhobienAdapter phobienAdapter;
    ArrayList<Playlist> playlists;


    private Dialog dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phobien, container, false);

        dialog = new Dialog(getActivity());
        dialog.show();

        phobienAdapter = new PhobienAdapter(getContext(), new ArrayList<Playlist>());
        recyclerviewphobien = view.findViewById(R.id.listviewphobien);
        tvphobien = view.findViewById(R.id.textviewphobien);
        getData();
        tvphobien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachcacplaylistActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GetPlaylistPopular();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlists = (ArrayList<Playlist>) response.body();
                phobienAdapter.setData(playlists);
                recyclerviewphobien.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                recyclerviewphobien.setAdapter(phobienAdapter);

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
}
