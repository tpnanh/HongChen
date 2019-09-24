package com.example.hongchen.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hongchen.Adapter.ViewPagerPlaylistnhac;
import com.example.hongchen.Fragment.Fragment_Dianhac;
import com.example.hongchen.Fragment.Fragment_Play_Danhsachbaihat;
import com.example.hongchen.Model.Baihat;
import com.example.hongchen.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    Toolbar toolbarplaynhac;
    TextView textview_time_song, textview_time_total;
    SeekBar seekbar_song;
    ImageButton imagebuttonshuffle, imagebuttonprevious, imagebuttonplay, imagebuttonnext, imagebuttonrepeat;
    ViewPager viewpagerplaynhac;
    Fragment_Play_Danhsachbaihat fragment_play_danhsachbaihat;
    Fragment_Dianhac fragment_dianhac;
    MediaPlayer mediaPlayer;
    public static ArrayList<Baihat> baihatArrayList = new ArrayList<>();
    private static ViewPagerPlaylistnhac adapterplaynhac;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean checknext = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterplaynhac.getItem(0) != null) {
                    if (baihatArrayList.size() > 0) {
                        fragment_dianhac.Playnhac(baihatArrayList.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        imagebuttonplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imagebuttonplay.setImageResource(R.drawable.open);
                    if (fragment_dianhac.objectAnimator != null){
                        fragment_dianhac.objectAnimator.pause();
                    }
                } else {
                    mediaPlayer.start();
                    imagebuttonplay.setImageResource(R.drawable.pause);
                    if (fragment_dianhac.objectAnimator != null){
                        fragment_dianhac.objectAnimator.resume();
                    }
                }
            }
        });
        imagebuttonrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imagebuttonrepeat.setImageResource(R.drawable.repeat);
                        imagebuttonshuffle.setImageResource(R.drawable.shufflesyned);
                    }
                    imagebuttonrepeat.setImageResource(R.drawable.repeat);
                    repeat = true;
                } else {
                    imagebuttonrepeat.setImageResource(R.drawable.syned);
                    repeat = false;
                }
            }
        });
        imagebuttonshuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imagebuttonshuffle.setImageResource(R.drawable.shuffle);
                        imagebuttonrepeat.setImageResource(R.drawable.syned);
                    }
                    imagebuttonshuffle.setImageResource(R.drawable.shuffle);
                    checkrandom = true;
                } else {
                    imagebuttonshuffle.setImageResource(R.drawable.shufflesyned);
                    checkrandom = false;
                }
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

        imagebuttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (baihatArrayList.size()>0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < baihatArrayList.size()) {
                        imagebuttonplay.setImageResource(R.drawable.pause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = baihatArrayList.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(baihatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (baihatArrayList.size()-1)) {
                            position = 0;
                        }
                        new Playmp3().execute(baihatArrayList.get(position).getLinkBaiHat());
                        fragment_dianhac.Playnhac(baihatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baihatArrayList.get(position).getTenBaiHat());
                        UpdateTimeSong();
                    }
                }

                imagebuttonprevious.setClickable(false);
                imagebuttonnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imagebuttonprevious.setClickable(true);
                        imagebuttonnext.setClickable(true);
                    }
                },5000);
            }
        });
        imagebuttonprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (baihatArrayList.size()>0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < baihatArrayList.size()) {
                        imagebuttonplay.setImageResource(R.drawable.pause);
                        position--;

                        if (position < 0) {
                            position = baihatArrayList.size() - 1;
                        }

                        if (repeat == true) {
                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(baihatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }

                        new Playmp3().execute(baihatArrayList.get(position).getLinkBaiHat());
                        fragment_dianhac.Playnhac(baihatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baihatArrayList.get(position).getTenBaiHat());
                        UpdateTimeSong();
                    }
                }

                imagebuttonprevious.setClickable(false);
                imagebuttonnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imagebuttonprevious.setClickable(true);
                        imagebuttonnext.setClickable(true);
                    }
                },5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        baihatArrayList.clear();
        if (intent != null) {
            if (intent.hasExtra("baihat")) {
                Baihat baihat = intent.getParcelableExtra("baihat");
                baihatArrayList.add(baihat);
            }

            if (intent.hasExtra("allbaihat")) {
                ArrayList<Baihat> ArrayListbaihat = intent.getParcelableArrayListExtra("allbaihat");
                baihatArrayList = ArrayListbaihat;
            }
        }
    }

    private void init() {
        toolbarplaynhac         = findViewById(R.id.toolbarplaynhac);
        textview_time_song      = findViewById(R.id.textview_time_song);
        textview_time_total     = findViewById(R.id.textview_time_total);
        seekbar_song            = findViewById(R.id.seekbar_song);
        imagebuttonshuffle      = findViewById(R.id.imagebuttonshuffle);
        imagebuttonnext         = findViewById(R.id.imagebuttonnext);
        imagebuttonplay         = findViewById(R.id.imagebuttonplay);
        imagebuttonprevious     = findViewById(R.id.imagebuttonpreview);
        imagebuttonrepeat       = findViewById(R.id.imagebuttonrepeat);
        viewpagerplaynhac       = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                baihatArrayList.clear();
            }
        });
        adapterplaynhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
        fragment_play_danhsachbaihat = new Fragment_Play_Danhsachbaihat();
        fragment_dianhac = new Fragment_Dianhac();
        adapterplaynhac.AddFragment(fragment_dianhac);
        adapterplaynhac.AddFragment(fragment_play_danhsachbaihat);
        viewpagerplaynhac.setAdapter(adapterplaynhac);
        fragment_dianhac = (Fragment_Dianhac) adapterplaynhac.getItem(0);
        if (baihatArrayList.size()>0) {
            getSupportActionBar().setTitle(baihatArrayList.get(0).getTenBaiHat());
            new Playmp3().execute(baihatArrayList.get(0).getLinkBaiHat());
            imagebuttonplay.setImageResource(R.drawable.pause);
        }
    }

    class Playmp3 extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();

                }
            });

            mediaPlayer.setDataSource(baihat);
            mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTimeSong();
        }
    }
    private void TimeSong() {
//        java.text.SimpleDateFormat Time = new SimpleDateFormat("mm:ss");
//        textview_time_total.setText(Time.format(mediaPlayer.getDuration()));
//        seekbar_song.setMax(mediaPlayer.getDuration());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textview_time_song.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekbar_song.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekbar_song.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textview_time_song.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 500);

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            checknext = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        }, 100);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checknext == true) {

                    if (position < baihatArrayList.size()) {
                        imagebuttonplay.setImageResource(R.drawable.pause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = baihatArrayList.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(baihatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (baihatArrayList.size()-1)) {
                            position = 0;
                        }
                        new Playmp3().execute(baihatArrayList.get(position).getLinkBaiHat());
                        fragment_dianhac.Playnhac(baihatArrayList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baihatArrayList.get(position).getTenBaiHat());
                    }

                    imagebuttonprevious.setClickable(false);
                    imagebuttonnext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imagebuttonprevious.setClickable(true);
                            imagebuttonnext.setClickable(true);
                        }
                    },5000);
                    checknext = false;
                    handler1.removeCallbacks(this);

                } else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
