package com.example.hongchen.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongchen.Adapter.SearchbaihatAdapter;
import com.example.hongchen.Model.Baihat;
import com.example.hongchen.R;
import com.example.hongchen.Service.APIService;
import com.example.hongchen.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    View view;
    Toolbar toolbarsearchbaihat;
    RecyclerView recyclerviewsearchbaihat;
    TextView textviewkhongdulieu;
    EditText edittextsearch;
    ImageView imageviewsearch;
    SearchbaihatAdapter searchbaihatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        DataIntent();
        anhxa();
        imageviewsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edittextsearch.getText().toString().isEmpty()) {
                    String tukhoa = edittextsearch.getText().toString();
                    SearchTukhoaBaihat(tukhoa);
                } else {
                    Toast.makeText(SearchActivity.this,"Chưa nhập tên bài hát!",Toast.LENGTH_LONG).show();
                }
            }
        });
//        init();
    }

    private void DataIntent() {
        Intent intent = getIntent();
    }

    private void init() {
        setSupportActionBar(toolbarsearchbaihat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarsearchbaihat.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbarsearchbaihat.setEnabled(false);
    }

    private void anhxa() {
//        toolbarsearchbaihat = findViewById(R.id.toolbarsearchbaihat);
        recyclerviewsearchbaihat = findViewById(R.id.recyclerviewsearchbaihat);
        textviewkhongdulieu = findViewById(R.id.textviewkhongdulieu);
        edittextsearch = findViewById(R.id.edittextsearch);
        imageviewsearch = findViewById(R.id.imageviewsearch);
    }

    private void SearchTukhoaBaihat(String query) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetSearchbaihat(query);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> baihatArrayList = (ArrayList<Baihat>) response.body();
                if (baihatArrayList.size()>0) {
                    searchbaihatAdapter = new SearchbaihatAdapter(SearchActivity.this,baihatArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
                    recyclerviewsearchbaihat.setLayoutManager(linearLayoutManager);
                    recyclerviewsearchbaihat.setAdapter(searchbaihatAdapter);
                    textviewkhongdulieu.setVisibility(View.GONE);
                    recyclerviewsearchbaihat.setVisibility(View.VISIBLE);
                } else {
                    textviewkhongdulieu.setVisibility(View.VISIBLE);
                    recyclerviewsearchbaihat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

}
