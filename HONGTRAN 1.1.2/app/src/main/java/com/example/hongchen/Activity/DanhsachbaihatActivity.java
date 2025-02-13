package com.example.hongchen.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;

import com.example.hongchen.Adapter.DanhsachbaihatAdapter;
import com.example.hongchen.Dialog.Dialog;
import com.example.hongchen.Model.Baihat;
import com.example.hongchen.Model.ChuDe;
import com.example.hongchen.Model.Playlist;
import com.example.hongchen.R;
import com.example.hongchen.Service.APIService;
import com.example.hongchen.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    Playlist playlist;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerviewdanhsachbaihat;
    ArrayList<Baihat> baihatArrayList;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    ImageView imageviewdanhsachbaihat;
    ChuDe chude;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dialog = new Dialog(this);
        dialog.show();

        DataIntent();
        anhxa();
        init();
        if(playlist != null && !playlist.getTen().equals("")) {
            setValueInView(playlist.getTen(), playlist.getHinhNen());
            getDataPlaylist(playlist.getIdPlayList());
        }

        if(chude != null && !chude.getTenChuDe().equals("")) {
            setValueInView(chude.getTenChuDe(), chude.getHinhChuDe());
            getDataChuDe((chude.getIdChuDe()));
        }
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        floatingActionButton.setEnabled(false);
//        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
//        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout =findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsachbaihat);
        recyclerviewdanhsachbaihat = findViewById(R.id.recyclerviewdanhsachbaihat);
        imageviewdanhsachbaihat = findViewById(R.id.imageviewdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null) {
            if(intent.hasExtra("itemplaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }

            if(intent.hasExtra("idchude")) {
                chude = (ChuDe) intent.getSerializableExtra("idchude");
            }
        }
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
//        try {
//            URL url = new URL(hinh);
//            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
//            collapsingToolbarLayout.setBackground(bitmapDrawable);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Picasso.get().load(hinh).into(imageviewdanhsachbaihat);
    }

    private void getDataChuDe(String idchude) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.Getdanhsachbaihattheochude(idchude);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihatArrayList = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,baihatArrayList);
                recyclerviewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerviewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);

                eventClick();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void getDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetDanhsachbaihattheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                baihatArrayList = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhsachbaihatActivity.this,baihatArrayList);
                recyclerviewdanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerviewdanhsachbaihat.setAdapter(danhsachbaihatAdapter);

                eventClick();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void eventClick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhsachbaihatActivity.this,PlayActivity.class);
                intent.putExtra("allbaihat",baihatArrayList);
                startActivity(intent);
            }
        });
    }
}
