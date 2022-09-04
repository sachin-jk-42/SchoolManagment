package com.example.schoolmangement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private ImageView profile;
    private TextView  name, aclass, roll_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile = findViewById(R.id.logo);
        name = findViewById(R.id.name_display);
        aclass = findViewById(R.id.class_display);
        roll_no = findViewById(R.id.roll_display);

        firebaseFirestore =  FirebaseFirestore.getInstance();
        firebaseFirestore.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                            for (QueryDocumentSnapshot data : task.getResult()) {
                                Map<String, Object> map = data.getData();
                                name.setText((String)map.get("name"));
                                aclass.setText((String)map.get("class"));
                                roll_no.setText((String)map.get("roll no"));
                                Glide.with(Profile.this).load((String)map.get("profile")).into(profile);
                            }
                    }
                });
    }
}