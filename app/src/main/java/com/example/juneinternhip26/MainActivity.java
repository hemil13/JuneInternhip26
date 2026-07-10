package com.example.juneinternhip26;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    //Variables
    EditText emailLogin, passwordLogin;

    TextView forgetPassword, createAccount;

    Button loginBtn;

    ImageView hide, show;

    //DB
    SQLiteDatabase db;

    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //DB
        db = openOrCreateDatabase(ConstantSp.pref, MODE_PRIVATE, null);

        String userTable = "CREATE TABLE IF NOT EXISTS user(userid INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR (50), email VARCHAR (100), contact VARCHAR (10), password VARCHAR (20))";
        db.execSQL(userTable);

        sp = getSharedPreferences(ConstantSp.pref, MODE_PRIVATE);


       //Connecct xml and java

        emailLogin = findViewById(R.id.email_login);
        passwordLogin = findViewById(R.id.password_login);
        loginBtn = findViewById(R.id.login_btn);
        forgetPassword = findViewById(R.id.forget_password);
        createAccount = findViewById(R.id.create_new_account);
        hide = findViewById(R.id.password_hide);
        show = findViewById(R.id.password_show);

        createAccount.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        });


        forgetPassword.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
        });

        loginBtn.setOnClickListener(view -> {

            String email = emailLogin.getText().toString().trim();
            String password = passwordLogin.getText().toString().trim();

            //Email Validation
            if(email.isEmpty()){
                emailLogin.setError("Enter Email");
                emailLogin.requestFocus();
                return;
            }

            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emailLogin.setError("Invalid Email");
                emailLogin.requestFocus();
                return;
            }

            // Password

            else if(password.isEmpty()){
                passwordLogin.setError("Enter Password");
                passwordLogin.requestFocus();
                return;
            }
            else if(password.length() < 6){
                passwordLogin.setError("Password must be of at least 6 characters");
                passwordLogin.requestFocus();
                return;
            }
            else {

                String checkUser = "SELECT * FROM user WHERE email = '"+email+"' AND password = '"+password+"'";

                Cursor cursor = db.rawQuery(checkUser, null);
                if(cursor.getCount() > 0){


                    while (cursor.moveToNext()) {
                        sp.edit().putInt(ConstantSp.userid, cursor.getInt(0)).commit();
                        sp.edit().putString(ConstantSp.name, cursor.getString(1)).commit();
                        sp.edit().putString(ConstantSp.email, cursor.getString(2)).commit();
                        sp.edit().putString(ConstantSp.contact, cursor.getString(3)).commit();
                        sp.edit().putString(ConstantSp.password, cursor.getString(4)).commit();

                        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(intent);

                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                }


//                Snackbar.make(view, "Login Successful", Snackbar.LENGTH_SHORT).setAction("Ok", v -> {
//                    Toast.makeText(MainActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();
//                }).show();
            }
        });


        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide.setVisibility(GONE);
                show.setVisibility(VISIBLE);
                passwordLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.setVisibility(GONE);
                hide.setVisibility(VISIBLE);
                passwordLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
    }
}