package com.example.hongchen.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.hongchen.Adapter.MainViewPagerAdapter;
import com.example.hongchen.Fragment.Fragment_Account;
import com.example.hongchen.Fragment.Fragment_HomePage;
import com.example.hongchen.Fragment.Fragment_Profile;
import com.example.hongchen.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class HomePage extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    private FirebaseAuth mAuth;

    //ArrayList<String>imagesUri=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);


        anhxa();

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            initLater();
        }else{
            init();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAuth.getCurrentUser() != null){
            initLater();
        }
    }

    public void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_HomePage(),"TRANG CHỦ");
        mainViewPagerAdapter.addFragment(new Fragment_Account(),"CÁ NHÂN");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    public void initLater() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_HomePage(),"TRANG CHỦ");
        mainViewPagerAdapter.addFragment(new Fragment_Profile(),"CÁ NHÂN");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    public void anhxa() {
        viewPager = findViewById(R.id.myViewPager);
        tabLayout = findViewById(R.id.myTabLayout);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}