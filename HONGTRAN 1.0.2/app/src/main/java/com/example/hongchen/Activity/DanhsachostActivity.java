package com.example.hongchen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.hongchen.Adapter.DanhsachostAdapter;
import com.example.hongchen.Model.Ost;
import com.example.hongchen.R;
import com.example.hongchen.Service.APIService;
import com.example.hongchen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachostActivity extends AppCompatActivity {

    Toolbar toolbardanhsachost;
    RecyclerView recyclerviewdanhsachost;
    DanhsachostAdapter danhsachostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachost);
        anhxa();
        init();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Ost>> callback = dataservice.GetDanhsachcacost();
        callback.enqueue(new Callback<List<Ost>>() {
            @Override
            public void onResponse(Call<List<Ost>> call, Response<List<Ost>> response) {
                ArrayList<Ost> ostArrayList = (ArrayList<Ost>) response.body();
                danhsachostAdapter = new DanhsachostAdapter(DanhsachostActivity.this,ostArrayList);
                recyclerviewdanhsachost.setLayoutManager(new GridLayoutManager(DanhsachostActivity.this,1));
                recyclerviewdanhsachost.setAdapter(danhsachostAdapter);
            }

            @Override
            public void onFailure(Call<List<Ost>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbardanhsachost);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ORIGINAL SOUNDTRACK");
        toolbardanhsachost.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void anhxa() {
        toolbardanhsachost = findViewById(R.id.toolbardanhsachost);
        recyclerviewdanhsachost = findViewById(R.id.recyclerviewdanhsachost);
    }
}
