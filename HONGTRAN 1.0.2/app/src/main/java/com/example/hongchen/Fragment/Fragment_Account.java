package com.example.hongchen.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongchen.Activity.ForgetPasswordActivity;
import com.example.hongchen.Activity.HomePage;
import com.example.hongchen.Activity.RegisterActivity;
import com.example.hongchen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Account extends Fragment {
    View view;

    private FirebaseAuth mAuth;

    private EditText etUserName, etPassword;
    private Button btnLogin;
    private TextView tvForgotPw, tvNoAccount;

    private ProgressDialog progressDialog;

    private DatabaseReference userRef;

    public static Fragment_Account newInstance() {
        return new Fragment_Account();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login,container,false);

        mAuth = FirebaseAuth.getInstance();
       // userRef = FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());

        etUserName = view.findViewById(R.id.edittextusername_login);
        etPassword = view.findViewById(R.id.edittextpassword_login);
        btnLogin = view.findViewById(R.id.buttonlogin);
        tvForgotPw = view.findViewById(R.id.textviewforgetpassword);
        tvNoAccount = view.findViewById(R.id.textviewregister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        tvNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        tvForgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), ForgetPasswordActivity.class);
                startActivity(intent1);
            }
        });

        return view;

    }

    private void Login() {
        String username_login = etUserName.getText().toString();
        String password_login = etPassword.getText().toString();
        if(TextUtils.isEmpty(username_login)){
            Toast.makeText(getActivity(), "You must enter your email", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password_login)){
            Toast.makeText(getActivity(), "You must enter your password", Toast.LENGTH_SHORT).show();
        }
        else{
            Validate(username_login,password_login);
        }
    }

    private void Validate(final String username_login, final String password_login) {
        mAuth.signInWithEmailAndPassword(username_login, password_login)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (mAuth.getCurrentUser().isEmailVerified()) {
                                Intent intent = new Intent(getActivity(),HomePage.class);
                                startActivity(intent);
                                getActivity().finish();
                            }
                            else {
                                Toast.makeText(getActivity(), "Account has not already verified", Toast.LENGTH_SHORT).show();
                                mAuth.signOut();
                            }
                        }
                        else {
                            Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            mAuth.signOut();
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getChildFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
