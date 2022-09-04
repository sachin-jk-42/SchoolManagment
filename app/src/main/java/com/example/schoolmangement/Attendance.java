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

public class Attendance extends AppCompatActivity {
    private ImageButton Home, Profile;
    private TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8;
    private TextView textView9, textView10, textView11, textView12,textView13;
    private FirebaseFirestore firebaseFirestore;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13);
        Home = findViewById(R.id.home);
        Profile = findViewById(R.id.profile);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Attendance.this, DashboardActivity.class));
                finish();
            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Attendance.this, Profile.class));
                finish();
            }
        });
        drawerLayout = findViewById(R.id.attendance_work);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("attendance").get()
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
                                textView1.setText("January: "+String.valueOf(hash.get("january"))+"/31");
                                textView2.setText("Febraury: "+String.valueOf(hash.get("february"))+"/28");
                                textView3.setText("March: "+String.valueOf(hash.get("march"))+"/31");
                                textView4.setText("April: "+String.valueOf(hash.get("april"))+"/30");
                                textView5.setText("May: "+String.valueOf(hash.get("may"))+"/31");
                                textView6.setText("June: "+String.valueOf(hash.get("june"))+"/30");
                                textView7.setText("July: "+String.valueOf(hash.get("july"))+"/31");
                                textView8.setText("August: "+String.valueOf(hash.get("august"))+"/31");
                                textView9.setText("September: "+String.valueOf(hash.get("september"))+"/30");
                                textView10.setText("Occtober: "+String.valueOf(hash.get("october"))+"/31");
                                textView11.setText("November: "+String.valueOf(hash.get("november"))+"/30");
                                textView12.setText("December: "+String.valueOf(hash.get("december"))+"/31");
                                textView13.setText("Total: "+String.valueOf(sum)+"/365");
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
                startActivity(new Intent(Attendance.this, DashboardActivity.class));
                finish();
                break;
            case R.id.nav_attendance:
                Toast.makeText(this, "Attendance", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Attendance.this, Attendance.class));
                finish();
                break;
            case R.id.nav_grades:
                Toast.makeText(this, "Grades", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Attendance.this, Grades.class));
                finish();
                break;
            case R.id.nav_homework:
                Toast.makeText(this, "Home Work", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Attendance.this, HomeWork.class));
                finish();
                break;
            case R.id.nav_notice:
                Toast.makeText(this, "Notice", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Attendance.this, Notice.class));
                finish();
                break;
            case R.id.nav_profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Attendance.this, Profile.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}