package com.example.hongchen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.hongchen.Adapter.DanhsachkttAdapter;
import com.example.hongchen.Model.Kichtruyenthanh;
import com.example.hongchen.R;
import com.example.hongchen.Service.APIService;
import com.example.hongchen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachkttActivity extends AppCompatActivity {

    Toolbar toolbardanhsachktt;
    RecyclerView recyclerviewdanhsachktt;
    DanhsachkttAdapter danhsachkttAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachktt);
        anhxa();
        init();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Kichtruyenthanh>> callback = dataservice.GetDanhsachcacktt();
        callback.enqueue(new Callback<List<Kichtruyenthanh>>() {
            @Override
            public void onResponse(Call<List<Kichtruyenthanh>> call, Response<List<Kichtruyenthanh>> response) {
                ArrayList<Kichtruyenthanh> kichtruyenthanhArrayList = (ArrayList<Kichtruyenthanh>) response.body();
                danhsachkttAdapter = new DanhsachkttAdapter(DanhsachkttActivity.this,kichtruyenthanhArrayList);
                recyclerviewdanhsachktt.setLayoutManager(new GridLayoutManager(DanhsachkttActivity.this,1));
                recyclerviewdanhsachktt.setAdapter(danhsachkttAdapter);
            }

            @Override
            public void onFailure(Call<List<Kichtruyenthanh>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbardanhsachktt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("KỊCH TRUYỀN THANH");
        toolbardanhsachktt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbardanhsachktt = findViewById(R.id.toolbardanhsachktt);
        recyclerviewdanhsachktt = findViewById(R.id.recyclerviewdanhsachktt);
    }
}
