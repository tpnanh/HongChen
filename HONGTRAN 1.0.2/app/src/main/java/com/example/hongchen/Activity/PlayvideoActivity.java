package com.example.hongchen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.hongchen.Model.Video;
import com.example.hongchen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PlayvideoActivity extends AppCompatActivity {

    Toolbar toolbarplayvideo;
    TextView textviewtenvideo, textviewtentacgia;
    VideoView videoview;
    ArrayList<Video> videoArrayList = new ArrayList<>();
    String url;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);
        GetDataFromIntent();
        init();
        onClick(url);
    }

    private void init() {
        toolbarplayvideo      = findViewById(R.id.toolbarplayvideo);
        textviewtenvideo      = findViewById(R.id.textviewtenvideo);
        textviewtentacgia     = findViewById(R.id.textviewtentacgia);
        videoview             = findViewById(R.id.videoview);
        setSupportActionBar(toolbarplayvideo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplayvideo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                videoArrayList.clear();
            }
        });
        mediaController = new MediaController(this);
        if (videoArrayList.size()>0) {
            getSupportActionBar().setTitle("HONGCHEN");
            url = videoArrayList.get(0).getLinkVideo();
            textviewtenvideo.setText(videoArrayList.get(0).getTenVideo());
            onClick(url);
        }
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("video")) {
                Video video = intent.getParcelableExtra("video");
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
        } catch (Exception e) {}
    }
}
