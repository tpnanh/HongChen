package com.example.hongchen.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hongchen.Model.Baihat;
import com.example.hongchen.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textview_name_song, textview_time_song, textview_time_total;
    SeekBar seekbar_song;
    ImageButton button_previous, button_play, button_next;
    MediaPlayer mediaPlayer;
    ArrayList<Song> arraySong;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mapping();
        AddSong();

        KhoiTaoMediaPlayer();

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                if(position > arraySong.size() - 1) {
                    position = 0;
                }
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                button_play.setImageResource(R.drawable.pause);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        button_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if(position < 0) {
                    position = arraySong.size() - 1;
                }
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                button_play.setImageResource(R.drawable.pause);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    button_play.setImageResource(R.drawable.open);
                } else {
                    mediaPlayer.start();
                    button_play.setImageResource(R.drawable.pause);
                }
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        seekbar_song.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekbar_song.getProgress());
            }
        });


    }

    private void UpdateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat Time = new SimpleDateFormat("mm:ss");
                textview_time_song.setText(Time.format(mediaPlayer.getCurrentPosition()));
                seekbar_song.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    private void SetTimeTotal(){
        java.text.SimpleDateFormat Time = new SimpleDateFormat("mm:ss");
        textview_time_total.setText(Time.format(mediaPlayer.getDuration()));
        seekbar_song.setMax(mediaPlayer.getDuration());
    }

    private void KhoiTaoMediaPlayer() {
        mediaPlayer = MediaPlayer.create(MainActivity.this, arraySong.get(position).getFile());
        textview_name_song.setText(arraySong.get(position).getName());
    }

    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("da_mei_jianghu", R.raw.da_mei_jianghu));
        arraySong.add(new Song("le_youji", R.raw.le_youji));
        arraySong.add(new Song("wu_nishang", R.raw.wu_nishang));
        arraySong.add(new Song("xiaocheng_yao", R.raw.xiaocheng_yao));
    }

    private void Mapping() {
        textview_time_song  = (TextView) findViewById(R.id.textview_time_song);
        textview_time_total = (TextView) findViewById(R.id.textview_time_total);
        textview_name_song  = (TextView) findViewById(R.id.textview_name_song);
        seekbar_song        = (SeekBar) findViewById(R.id.seekbar_song);
        button_next         = (ImageButton) findViewById(R.id.button_next);
        button_play         = (ImageButton) findViewById(R.id.button_play);
        button_previous     = (ImageButton) findViewById(R.id.button_previous);
    }

}
