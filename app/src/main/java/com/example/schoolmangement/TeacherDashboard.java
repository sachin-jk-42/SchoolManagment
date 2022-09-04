package com.example.schoolmangement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TeacherDashboard extends AppCompatActivity {
    private Button homeWork, notice, attendance, grades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        grades = findViewById(R.id.grades);
        attendance = findViewById(R.id.attendance);
        homeWork = findViewById(R.id.homework);
        notice = findViewById(R.id.tnotice);

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TeacherDashboard.this, TeacherNotice.class));
            }
        });
        homeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("work in progress");
            }
        });
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("work in progress");
            }
        });
        grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("work in progress");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}