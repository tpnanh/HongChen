package com.example.hongchen.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hongchen.Fragment.Fragment_Account;
import com.example.hongchen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUserName, etPassword, etPassword2;
    private CheckBox ckbAgree;
    private Button btnRegister;

    private ProgressDialog progressDialog;

    private DatabaseReference userRef;
    private FirebaseAuth mAuth;


    public static String USERNAME = "username";
    public static String PASSWORD = "password";

    private String username, password, repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        progressDialog = new ProgressDialog(RegisterActivity.this);

        etUserName = findViewById(R.id.edittextusername_register);
        etPassword = findViewById(R.id.edittextpassword_register);
        etPassword2 = findViewById(R.id.edittextrepassword_register);

        ckbAgree = findViewById(R.id.checkboxregister);

        btnRegister = findViewById(R.id.buttonregister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }

    private void Register() {
        username = etUserName.getText().toString();
        password = etPassword.getText().toString();
        repassword = etPassword2.getText().toString();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "You must enter your email", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "You must enter your password", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(repassword)) {
            Toast.makeText(this, "You must confirm your password", Toast.LENGTH_SHORT).show();
        }
        else if(password.length()<6 && password.length()>20){
            Toast.makeText(this, "Password must be 6-20 characters", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(repassword)){
            Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
        }
        else if(!ckbAgree.isChecked()){
            Toast.makeText(this, "You must accept the license", Toast.LENGTH_SHORT).show();
        }
        else{
            createAccount(username,password);
        }
    }

    private void createAccount(final String username, final String password) {
        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Account was created", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateDatabase(user.getUid());
                        } else {
                            Toast.makeText(RegisterActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void updateDatabase(final String uid) {
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    HashMap<String, Object> userdata = new HashMap<>();
                    userdata.put("Email",uid);
                    userdata.put("Password",password);
                    userRef.child("User").child(uid).updateChildren(userdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                sendVerification();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"Register failed",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void sendVerification() {

        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Your account has been created, please check your email",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        mAuth.signOut();
        finish();
    }
}
