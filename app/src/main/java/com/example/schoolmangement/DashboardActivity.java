package com.example.schoolmangement;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class DashboardActivity extends AppCompatActivity {

     private ImageButton Home, Profile;
     private Button homeWork, notice, attendance, grades;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initialize();
        listener();
    }

    private void listener() {
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, DashboardActivity.class));
                finish();
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, Profile.class));
            }
        });
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, Notice.class));
            }
        });
        homeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, HomeWork.class));
            }
        });
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, Attendance.class));
            }
        });
        grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, Grades.class));
            }
        });
    }

    private void initialize() {
        Home = findViewById(R.id.home);
        Profile = findViewById(R.id.profile);
        grades = findViewById(R.id.grades);
        attendance = findViewById(R.id.attendance);
        homeWork = findViewById(R.id.homework);
        notice = findViewById(R.id.notice);
        drawerLayout = findViewById(R.id.mu_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_dashboard:
                Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashboardActivity.this, DashboardActivity.class));
                finish();
                break;
            case R.id.nav_attendance:
                Toast.makeText(this, "Attendance", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashboardActivity.this, Attendance.class));
                finish();
                break;
            case R.id.nav_grades:
                Toast.makeText(this, "Grades", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashboardActivity.this, Grades.class));
                finish();
                break;
            case R.id.nav_homework:
                Toast.makeText(this, "Home Work", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashboardActivity.this, HomeWork.class));
                finish();
                break;
            case R.id.nav_notice:
                Toast.makeText(this, "Notice", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashboardActivity.this, Notice.class));
                finish();
                break;
            case R.id.nav_profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashboardActivity.this, Profile.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}