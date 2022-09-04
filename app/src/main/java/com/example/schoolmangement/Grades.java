package com.example.schoolmangement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class Grades extends AppCompatActivity {
    private ImageButton Home, Profile;
    private TextView english, kannada, hindi, maths, science, social,total;
    private FirebaseFirestore firebaseFirestore;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        Home = findViewById(R.id.home);
        Profile = findViewById(R.id.profile);
        english = findViewById(R.id.english_grad);
        kannada = findViewById(R.id.kannada_grad);
        hindi = findViewById(R.id.hindi_grad);
        maths = findViewById(R.id.maths_grad);
        science = findViewById(R.id.science_grad);
        social = findViewById(R.id.social_grad);
        total = findViewById(R.id.total_grad);


        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Grades.this, DashboardActivity.class));
                finish();
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Grades.this, Profile.class));
                finish();
            }
        });
        drawerLayout = findViewById(R.id.grade_work);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("grades").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                            for (QueryDocumentSnapshot data : task.getResult()) {
                                Map<String,Object> hash= data.getData();
                                int sum = 0;
                                for (Object date : hash.values()) {
                                    sum+=(long)Integer.parseInt(String.valueOf(date));
                                }
                                english.setText(String.valueOf(hash.get("english"))+"/100");
                                kannada.setText(String.valueOf(hash.get("kannada"))+"/100");
                                hindi.setText(String.valueOf(hash.get("hindi"))+"/100");
                                maths.setText(String.valueOf(hash.get("maths"))+"/100");
                                science.setText(String.valueOf(hash.get("science"))+"/100");
                                social.setText(String.valueOf(hash.get("social"))+"/100");
                                total.setText(String.valueOf(sum)+"/600");
                            }
                    }
                });
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
                startActivity(new Intent(Grades.this, DashboardActivity.class));
                finish();
                break;
            case R.id.nav_attendance:
                Toast.makeText(this, "Attendance", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Grades.this, Attendance.class));
                finish();
                break;
            case R.id.nav_grades:
                Toast.makeText(this, "Grades", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Grades.this, Grades.class));
                finish();
                break;
            case R.id.nav_homework:
                Toast.makeText(this, "Home Work", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Grades.this, HomeWork.class));
                finish();
                break;
            case R.id.nav_notice:
                Toast.makeText(this, "Notice", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Grades.this, Notice.class));
                finish();
                break;
            case R.id.nav_profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Grades.this, Profile.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}