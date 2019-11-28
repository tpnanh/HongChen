package com.example.hongchen.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.hongchen.Fragment.Fragment_Profile;
import com.example.hongchen.R;

public class Question extends AppCompatActivity {
    private TextView ques1, ques2,
                ans1, ans2;

    private ScrollView scrollView;

    private Boolean chk1 = false,chk2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ques1 = findViewById(R.id.tv_ques1);
        ques2 = findViewById(R.id.tv_ques2);
        ans1 = findViewById(R.id.tv_ans1);
        ans2 = findViewById(R.id.tv_ans2);
        scrollView = findViewById(R.id.svProfile);

        ques1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chk1 == false){
                    ans1.setVisibility(View.VISIBLE);
                    chk1 = true;
                }
                else {
                    ans1.setVisibility(View.GONE);
                    chk1 = false;
                }
            }
        });

        ques2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chk2 == false){
                    ans2.setVisibility(View.VISIBLE);
                    chk2 = true;
                }
                else {
                    ans2.setVisibility(View.GONE);
                    chk2 = false;
                }
            }
        });

    }
}
