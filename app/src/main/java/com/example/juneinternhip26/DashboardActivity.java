package com.example.juneinternhip26;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    //Variables
    Button deleteProfile, logout;

    SharedPreferences sp;

    String email;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        db = openOrCreateDatabase("JuneInternhip26", MODE_PRIVATE, null);

        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (50), email VARCHAR (100), contact VARCHAR (10), password VARCHAR (20))";
        db.execSQL(userTable);

        sp = getSharedPreferences("JuneInternship26", MODE_PRIVATE);

        email = sp.getString("email", null);


        deleteProfile = findViewById(R.id.dashboard_delete);
        logout = findViewById(R.id.dashboard_logout);

        deleteProfile.setOnClickListener(view -> {
            String deleteProfile = "DELETE FROM user WHERE email = '"+email+"'";
            db.execSQL(deleteProfile);
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Profile Deleted Successfully", Toast.LENGTH_SHORT).show();
        });

        logout.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}