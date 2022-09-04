package com.example.schoolmangement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class TeacherNotice extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private Button push;
    private EditText notice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_notice);

        notice = findViewById(R.id.notice_input);
        push = findViewById(R.id.psuh);

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("message",notice.getText().toString());
                firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore.collection("notice").add(map)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(TeacherNotice.this, "notice pushed..", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(TeacherNotice.this, TeacherDashboard.class));
                            }
                        });
            }
        });

    }
}