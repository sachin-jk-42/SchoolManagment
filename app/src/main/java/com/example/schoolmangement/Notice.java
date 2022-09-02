package com.example.schoolmangement;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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

public class Notice extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<String> noticeList = new ArrayList<>();
    private RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        list = findViewById(R.id.notice_list);
        NoticeAdapter adapter = new NoticeAdapter(getApplicationContext(),noticeList);
        ArrayAdapter<String> noticeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,noticeList);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext());
        list.setAdapter(noticeAdapter);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("notice").orderBy("message", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        for (QueryDocumentSnapshot data : task.getResult()) {
                            String temp = data.toObject(String.class);
                            noticeList.add(temp);
                        }
                        noticeAdapter.notifyDataSetChanged();
                    }
                });


    }
}