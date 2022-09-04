package com.example.schoolmangement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

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

        drawerLayout = findViewById(R.id.home_work);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
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
                startActivity(new Intent(HomeWork.this, DashboardActivity.class));
                finish();
                break;
            case R.id.nav_attendance:
                Toast.makeText(this, "Attendance", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeWork.this, Attendance.class));
                finish();
                break;
            case R.id.nav_grades:
                Toast.makeText(this, "Grades", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeWork.this, Grades.class));
                finish();
                break;
            case R.id.nav_homework:
                Toast.makeText(this, "Home Work", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeWork.this, HomeWork.class));
                finish();
                break;
            case R.id.nav_notice:
                Toast.makeText(this, "Notice", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeWork.this, Notice.class));
                finish();
                break;
            case R.id.nav_profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeWork.this, Profile.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}