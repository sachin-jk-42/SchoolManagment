package com.example.schoolmangement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
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
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
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

        drawerLayout = findViewById(R.id.notice_work);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
//        list.setHasFixedSize(true);
        NoticeAdapter adapter = new NoticeAdapter(getApplicationContext(),noticeList);
        list.setAdapter(adapter);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("notice").orderBy("message", Query.Direction.ASCENDING).get()
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
                startActivity(new Intent(Notice.this, DashboardActivity.class));
                finish();
                break;
            case R.id.nav_attendance:
                Toast.makeText(this, "Attendance", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Notice.this, Attendance.class));
                finish();
                break;
            case R.id.nav_grades:
                Toast.makeText(this, "Grades", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Notice.this, Grades.class));
                finish();
                break;
            case R.id.nav_homework:
                Toast.makeText(this, "Home Work", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Notice.this, HomeWork.class));
                finish();
                break;
            case R.id.nav_notice:
                Toast.makeText(this, "Notice", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Notice.this, Notice.class));
                finish();
                break;
            case R.id.nav_profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Notice.this, Profile.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}