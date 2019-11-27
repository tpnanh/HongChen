package com.example.hongchen.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.hongchen.Dialog.Dialog;
import com.example.hongchen.Model.Kichtruyenthanh;
import com.example.hongchen.Model.Video;
import com.example.hongchen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class PlaykttActivity extends AppCompatActivity {

    Toolbar toolbarplayktt;
    TextView textviewtenktt, textviewluotthich;
    VideoView kttview;
    ArrayList<Kichtruyenthanh> kichtruyenthanhArrayList = new ArrayList<>();
    String url, id;
    ImageButton ibLike;
    MediaController mediaController;

    DatabaseReference loveRef;
    FirebaseAuth mAuth;
    private boolean checkIB = true;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playktt);

        dialog = new Dialog(this);
        dialog.show();

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null) {
            loveRef = FirebaseDatabase.getInstance().getReference().child("Love").child(mAuth.getCurrentUser().getUid());
        }
        else{
            loveRef = FirebaseDatabase.getInstance().getReference();
        }
        GetDataFromIntent();
        init();
        onClick(url);
    }

    private void init() {
        toolbarplayktt          = findViewById(R.id.toolbarplayktt);
        textviewtenktt          = findViewById(R.id.textviewtenktt);
        textviewluotthich   = findViewById(R.id.textviewluotthich);
        kttview                 = findViewById(R.id.kttview);
        ibLike                  = findViewById(R.id.imagebuttonlike_ktt);
        setSupportActionBar(toolbarplayktt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplayktt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ibLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIB==true){
                    ibLike.setImageResource(R.drawable.love);
                    checkIB = false;
                }
                else{
                    checkIB = true;
                    ibLike.setImageResource(R.drawable.nolove);
                }
            }
        });

        mediaController = new MediaController(this);
        if (kichtruyenthanhArrayList.size()>0) {
            getSupportActionBar().setTitle("HONGCHEN");
            url = kichtruyenthanhArrayList.get(0).getLinkKTT();
            textviewtenktt.setText(kichtruyenthanhArrayList.get(0).getTenKTT());
            onClick(url);
        }
        getLoadData();
    }

    private void getLoadData() {
        id = url.replace(".","").replace("/","");
        if (mAuth.getCurrentUser()!=null) {
            loveRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        ibLike.setImageResource(R.drawable.love);
                        checkIB = false;
                    } else {
                        checkIB = true;
                        ibLike.setImageResource(R.drawable.nolove);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else {
            checkIB = true;
            ibLike.setImageResource(R.drawable.nolove);
        }
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {

            if (intent.hasExtra("ktt")) {
                Kichtruyenthanh ktt = intent.getParcelableExtra("ktt");
                kichtruyenthanhArrayList.add(ktt);
            }

            if (intent.hasExtra("MucYeuThichItem")){
                String ten = intent.getStringExtra("tenBaiHat");
                String link = intent.getStringExtra("linkBaiHat");
                Kichtruyenthanh kichtruyenthanh = new Kichtruyenthanh(ten, link);
                kichtruyenthanhArrayList.add(kichtruyenthanh);
            }
        }
    }

    public void onClick(String videourl) {
        try {
            Uri uri = Uri.parse(videourl);
            kttview.setVideoURI(uri);
            kttview.setMediaController(mediaController);
            mediaController.setAnchorView(kttview);
            kttview.start();

            kttview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    dialog.dismiss();
                }
            });

        } catch (Exception e) {}
    }


    @Override
    protected void onPause() {
        super.onPause();
        loveRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (checkIB==true){
                    // chua like
                    if (dataSnapshot.exists()){
                        loveRef.child(id).removeValue();
                    }
                }else{
                    HashMap<String,Object> hashMap = new HashMap<>();
                    if (!dataSnapshot.exists()){

                        hashMap.put("Id",id);
                        hashMap.put("Url",url);
                        hashMap.put("Name",kichtruyenthanhArrayList.get(0).getTenKTT());
                        hashMap.put("Type","KTT");
                        hashMap.put("Image",kichtruyenthanhArrayList.get(0).getHinhKTT());
                        hashMap.put("Luotthich",kichtruyenthanhArrayList.get(0).getLuotThich());
                    }else{
                    }
                    loveRef.child(id).updateChildren(hashMap);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
