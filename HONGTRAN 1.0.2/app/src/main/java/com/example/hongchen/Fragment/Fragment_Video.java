package com.example.hongchen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hongchen.Adapter.VideoAdapter;
import com.example.hongchen.Activity.DanhsachvideoActivity;
import com.example.hongchen.Model.Video;
import com.example.hongchen.R;
import com.example.hongchen.Service.APIService;
import com.example.hongchen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Video extends Fragment {
    View view;
    RecyclerView recyclerviewvideo;
    TextView textviewvideo;
    VideoAdapter videoAdapter;
    ArrayList<Video> videoArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video,container,false);
        recyclerviewvideo = view.findViewById(R.id.listviewvideo);
        textviewvideo = view.findViewById(R.id.textviewvideo);
        getData();
        textviewvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhsachvideoActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Video>> callback = dataservice.GetVideo();
        callback.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                videoArrayList = (ArrayList<Video>) response.body();
                videoAdapter = new VideoAdapter(getActivity(),videoArrayList);
                recyclerviewvideo.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerviewvideo.setAdapter(videoAdapter);
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });
    }
}
