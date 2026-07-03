package com.example.juneinternhip26;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    EditText name, email, contact, password, cnfPassword;
    Button edit, update;

    SharedPreferences sp;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sp = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);
        db = openOrCreateDatabase(ConstantSp.pref, MODE_PRIVATE, null);
        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (50), email VARCHAR (100), contact VARCHAR (10), password VARCHAR (20))";
        db.execSQL(userTable);


        name=findViewById(R.id.name_profile);
        email=findViewById(R.id.email_profile);
        contact=findViewById(R.id.contact_profile);
        password=findViewById(R.id.password_profile);
        cnfPassword=findViewById(R.id.confirm_password_profile);
        edit=findViewById(R.id.profile_btn);
        update=findViewById(R.id.update_profile_btn);

        name.setText(sp.getString(ConstantSp.name,""));
        email.setText(sp.getString(ConstantSp.email,""));
        contact.setText(sp.getString(ConstantSp.contact,""));
        password.setText(sp.getString(ConstantSp.password,""));
        
        setData(false);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.setVisibility(View.GONE);
                update.setVisibility(View.VISIBLE);
                cnfPassword.setVisibility(View.VISIBLE);

                setData(true);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String updateUser = "UPDATE user SET name = '"+name.getText().toString()+"', " +
                        "email = '"+email.getText().toString()+"'," +
                        " contact = '"+contact.getText().toString()+"', " +
                        "password = '"+password.getText().toString()+"' " +
                        "WHERE userid = '"+sp.getInt(ConstantSp.userid,0)+"'";
                db.execSQL(updateUser);

                sp.edit().putString(ConstantSp.name, name.getText().toString()).commit();
                sp.edit().putString(ConstantSp.email, email.getText().toString()).commit();
                sp.edit().putString(ConstantSp.contact, contact.getText().toString()).commit();
                sp.edit().putString(ConstantSp.password, password.getText().toString()).commit();

                Toast.makeText(ProfileActivity.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();



                edit.setVisibility(View.VISIBLE);
                update.setVisibility(View.GONE);
                cnfPassword.setVisibility(View.GONE);
                setData(false);
            }
        });
    }

    private void setData(boolean b) {
        name.setEnabled(b);
        email.setEnabled(b);
        contact.setEnabled(b);
        password.setEnabled(b);
    }
}