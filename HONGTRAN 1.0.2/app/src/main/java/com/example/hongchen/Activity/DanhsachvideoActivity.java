package com.example.hongchen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.example.hongchen.Adapter.DanhsachvideoAdapter;
import com.example.hongchen.Model.Video;
import com.example.hongchen.R;
import com.example.hongchen.Service.APIService;
import com.example.hongchen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachvideoActivity extends AppCompatActivity {

    Toolbar toolbardanhsachvideo;
    RecyclerView recyclerviewdanhsachvideo;
    DanhsachvideoAdapter danhsachvideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachvideo);
        anhxa();
        init();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Video>> callback = dataservice.GetDanhsachcacvideo();
        callback.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                ArrayList<Video> videoArrayList = (ArrayList<Video>) response.body();
                danhsachvideoAdapter = new DanhsachvideoAdapter(DanhsachvideoActivity.this,videoArrayList);
                recyclerviewdanhsachvideo.setLayoutManager(new GridLayoutManager(DanhsachvideoActivity.this,2));
                recyclerviewdanhsachvideo.setAdapter(danhsachvideoAdapter);
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbardanhsachvideo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("VIDEO");
        toolbardanhsachvideo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbardanhsachvideo = findViewById(R.id.toolbardanhsachvideo);
        recyclerviewdanhsachvideo = findViewById(R.id.recyclerviewdanhsachvideo);
    }
}
