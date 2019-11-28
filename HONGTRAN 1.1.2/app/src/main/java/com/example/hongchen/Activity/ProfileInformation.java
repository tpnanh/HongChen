package com.example.hongchen.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hongchen.Model.User;
import com.example.hongchen.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileInformation extends AppCompatActivity {
    private TextView profileEmail, profilePassword;

    private FirebaseAuth mAuth;

    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_information);

        profileEmail = findViewById(R.id.profile_email);
        profilePassword = findViewById(R.id.profile_password);

        mAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference();

        userRef.child("User").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                profileEmail.setText(mAuth.getCurrentUser().getEmail());

                User user = dataSnapshot.getValue(User.class);

                String password = user.getPassword();
                String pw = password.substring(password.length()-2);

                profilePassword.setText("****" + pw);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }




}
