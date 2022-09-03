package com.example.schoolmangement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class Notice extends AppCompatActivity {
    private ImageButton Home, Profile;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<String> noticeList = new ArrayList<>();
    private RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Home = findViewById(R.id.home);
        Profile = findViewById(R.id.profile);
        list = findViewById(R.id.notice_list);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Notice.this, DashboardActivity.class));
                finish();
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Notice.this, Profile.class));
                finish();
            }
        });
        list.setLayoutManager(new LinearLayoutManager(this));
//        list.setHasFixedSize(true);
        NoticeAdapter adapter = new NoticeAdapter(getApplicationContext(),noticeList);
        list.setAdapter(adapter);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("notice").orderBy("message", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        for (QueryDocumentSnapshot data : task.getResult()) {
                            Map<String,Object> hash= data.getData();
                            noticeList.add((String)hash.get("message"));
                        }
                        Log.v("message",noticeList.toString());
                        adapter.notifyDataSetChanged();
                    }
                });


    }
}