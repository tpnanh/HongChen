package com.example.hongchen.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongchen.Activity.HomePage;
import com.example.hongchen.Activity.MucyeuthichActivity;
import com.example.hongchen.Activity.ProfileInformation;
import com.example.hongchen.Activity.Question;
import com.example.hongchen.Model.User;
import com.example.hongchen.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Profile extends Fragment {
    private Context context;
    View view;
    private TextView tvUserName, tvLogout, tvLike, tvQuestion, tvInformation;
    private CircleImageView ivAvatar;

    private FirebaseAuth mAuth;
    private DatabaseReference userRef;

    private Uri imageUri = null;

    private StorageReference storageProfilePictureRef;
    private StorageTask uploadTask;

    private Dialog dialog;

    public static Fragment_Profile newInstance() {
        return new Fragment_Profile();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        context = this.getContext();

        ivAvatar = view.findViewById(R.id.imageviewavatar);
        tvUserName = view.findViewById(R.id.textviewUsername);
        tvLike = view.findViewById(R.id.textviewlike);
        tvLogout = view.findViewById(R.id.textviewlogout);
        tvQuestion = view.findViewById(R.id.textviewquestion);
        tvInformation = view.findViewById(R.id.textviewinformation);

        userRef = FirebaseDatabase.getInstance().getReference();
        storageProfilePictureRef = FirebaseStorage.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();

        tvInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in2 = new Intent(getActivity(), ProfileInformation.class);
                startActivity(in2);
            }
        });

        ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setAspectRatio(1, 1)
                        .start(context, Fragment_Profile.this);
            }
        });

        tvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MucyeuthichActivity.class);
                startActivity(i);
            }
        });

        tvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), Question.class);
                startActivity(in);
            }
        });

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), HomePage.class);
                startActivity(intent);
            }
        });

        dowloadDataUser();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
                && resultCode == Activity.RESULT_OK && data != null) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();
            uploadStorageFirebase();
        } else {
            Toast.makeText(getActivity(), "Error. Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadStorageFirebase() {
        if (imageUri != null) {
            final StorageReference fileRef = storageProfilePictureRef.child("Image Profile").child(mAuth.getCurrentUser().getUid())
                    .child(mAuth.getCurrentUser().getUid()+".jpg");
            uploadTask = fileRef.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()){
                        uploadDatabaseFirebase(task.getResult());
                    }
                }
            });
        }
    }

    private void uploadDatabaseFirebase(Object result) {
        String image = result.toString();
        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("image",image);
        userRef.child("User").child(mAuth.getCurrentUser().getUid()).updateChildren(hashMap);
        dowloadDataUser();
    }

    private void dowloadDataUser(){
        userRef.child("User").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                tvUserName.setText(user.getUserName());

                if (user.getImage().equals("")){
                   ivAvatar.setImageResource(R.drawable.ic_launcher_background);
                }else{
                    Picasso.get().load(user.getImage()).into(ivAvatar);
               }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

}
