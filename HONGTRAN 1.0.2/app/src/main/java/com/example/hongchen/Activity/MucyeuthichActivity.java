package com.example.hongchen.Activity;

import android.os.Bundle;
import android.view.View;

import com.example.hongchen.Adapter.MucyeuthichAdapter;
import com.example.hongchen.Model.YeuThich;
import com.example.hongchen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MucyeuthichActivity extends AppCompatActivity {
    Toolbar toolbarmucyeuthich;
    RecyclerView recyclerviewmucyeuthich;

    private DatabaseReference yeuThichRef;
    private FirebaseAuth mAuth;

    private MucyeuthichAdapter adapter;

    private ArrayList<YeuThich> data;

    private boolean pause = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mucyeuthich);

        mAuth = FirebaseAuth.getInstance();
        yeuThichRef = FirebaseDatabase.getInstance().getReference().child("Love").child(mAuth.getCurrentUser().getUid());

        data = new ArrayList<>();

        anhxa();
        init();
    }

    private void init() {
        setSupportActionBar(toolbarmucyeuthich);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("BÀI HÁT YÊU THÍCH");
        toolbarmucyeuthich.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pause == true) {
            data.clear();
            loadData();
            pause = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause = true;
    }

    private void anhxa() {
        toolbarmucyeuthich = findViewById(R.id.toolbarmucyeuthich);
        recyclerviewmucyeuthich = findViewById(R.id.recyclerviewmucyeuthich);
        loadData();
    }



    private void loadData() {
        yeuThichRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot children :dataSnapshot.getChildren()){

                    YeuThich yeuThich = children.getValue(YeuThich.class);
                    data.add(yeuThich);
                }

                setAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setAdapter(){
        adapter = new MucyeuthichAdapter(MucyeuthichActivity.this, data);
        recyclerviewmucyeuthich.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewmucyeuthich.setAdapter(adapter);
    }
}
