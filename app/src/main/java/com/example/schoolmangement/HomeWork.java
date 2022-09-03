package com.example.schoolmangement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class HomeWork extends AppCompatActivity {
    private ImageButton Home, Profile;
    private TextView english, kannada, hindi, maths, science, social;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work);

        Home = findViewById(R.id.home);
        Profile = findViewById(R.id.profile);
        english = findViewById(R.id.english_homework);
        kannada = findViewById(R.id.kannada_homework);
        hindi = findViewById(R.id.hindi_homework);
        maths = findViewById(R.id.maths_homework);
        science = findViewById(R.id.science_homework);
        social = findViewById(R.id.social_homework);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeWork.this, DashboardActivity.class));
                finish();
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeWork.this, Profile.class));
                finish();
            }
        });
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("home work").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                            for (QueryDocumentSnapshot data : task.getResult()) {
                                Map<String,Object> hash= data.getData();
                                english.setText((String)hash.get("english"));
                                kannada.setText((String)hash.get("kannada"));
                                hindi.setText((String)hash.get("hindi"));
                                maths.setText((String)hash.get("maths"));
                                science.setText((String)hash.get("science"));
                                social.setText((String)hash.get("social"));
                            }
                    }
                });

    }
}