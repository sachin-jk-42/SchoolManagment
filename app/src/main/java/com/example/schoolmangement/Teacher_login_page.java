package com.example.schoolmangement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Teacher_login_page extends AppCompatActivity {
    private FirebaseDatabase loginDB;
    private EditText Username;
    private EditText Password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login_page);
        ConstraintLayout constraintLayout = findViewById(R.id.layout);

//        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
//        startActivity(intent);

        Username = findViewById(R.id.sign_in_temail_input);
        Password = findViewById(R.id.sign_in_tpassword_input);
        login = findViewById(R.id.Sign_in_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginDB = FirebaseDatabase.getInstance();
                DatabaseReference ref = loginDB.getReference("teacher");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean is_matching = false;
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            User user = snap.getValue(User.class);
                            String email_string = Username.getText().toString();
                            String password_string = Password.getText().toString();
                            if (user.getUser().equals(email_string) && user.getPassword().equals(password_string)) {
                                is_matching = true;
                                Intent intent = new Intent(getApplicationContext(), TeacherDashboard.class);
                                startActivity(intent);
                                break;
                            }
                        }
                        if (!is_matching) {
                            Toast.makeText(Teacher_login_page.this, "invalid credentials...", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Teacher_login_page.this, "Database error : " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}