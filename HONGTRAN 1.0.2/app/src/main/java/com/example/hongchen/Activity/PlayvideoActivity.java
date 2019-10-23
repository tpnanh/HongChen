package com.example.hongchen.Activity;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class PlayvideoActivity extends AppCompatActivity {

    Toolbar toolbarplayvideo;
    TextView textviewtenvideo, textviewtentacgia;
    VideoView videoview;
    ArrayList<Video> videoArrayList = new ArrayList<>();
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
        setContentView(R.layout.activity_playvideo);

        dialog = new Dialog(this);
        dialog.show();

        mAuth = FirebaseAuth.getInstance();
        loveRef = FirebaseDatabase.getInstance().getReference().child("Love").child(mAuth.getCurrentUser().getUid());

        GetDataFromIntent();
        init();
        onClick(url);
    }

    private void init() {
        toolbarplayvideo      = findViewById(R.id.toolbarplayvideo);
        textviewtenvideo      = findViewById(R.id.textviewtenvideo);
        textviewtentacgia     = findViewById(R.id.textviewtentacgia);
        videoview             = findViewById(R.id.videoview);
        ibLike                = findViewById(R.id.imagebuttonlike);
        setSupportActionBar(toolbarplayvideo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplayvideo.setNavigationOnClickListener(new View.OnClickListener() {
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
        if (videoArrayList.size()>0) {
            getSupportActionBar().setTitle("HONGCHEN");
            url = videoArrayList.get(0).getLinkVideo();
            textviewtenvideo.setText(videoArrayList.get(0).getTenVideo());
            onClick(url);
        }
        getLoadData();
    }

    private void getLoadData() {
        id = url.replace(".","").replace("/","");
        loveRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    ibLike.setImageResource(R.drawable.love);
                    checkIB = false;
                }else{
                    checkIB = true;
                    ibLike.setImageResource(R.drawable.nolove);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("video")) {
                Video video = intent.getParcelableExtra("video");
                videoArrayList.add(video);
            }

            if (intent.hasExtra("MucYeuThichItem")){
                String ten = intent.getStringExtra("tenBaiHat");
                String link = intent.getStringExtra("linkBaiHat");
                Video video = new Video(ten, link);
                videoArrayList.add(video);
            }
        }
    }

    public void onClick(String videourl) {
        try {
            Uri uri = Uri.parse(videourl);
            videoview.setVideoURI(uri);
            videoview.setMediaController(mediaController);
            mediaController.setAnchorView(videoview);
            videoview.start();

            videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
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
                        hashMap.put("Name",videoArrayList.get(0).getTenVideo());
                        hashMap.put("Type","MV");
                        hashMap.put("Image",videoArrayList.get(0).getHinhVideo());
                        hashMap.put("TacGia",videoArrayList.get(0).getTacGia());
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
