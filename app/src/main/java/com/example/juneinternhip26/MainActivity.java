package com.example.juneinternhip26;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
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

    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

       //Connecct xml and java

        emailLogin = findViewById(R.id.email_login);
        passwordLogin = findViewById(R.id.password_login);
        loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(view -> {

            String email = emailLogin.getText().toString().trim();
            String password = passwordLogin.getText().toString().trim();

            //Email Validation
            if(email.isEmpty()){
                emailLogin.setError("Enter Email");
                emailLogin.requestFocus();
                return;
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                emailLogin.setError("Invalid Email");
                emailLogin.requestFocus();
                return;
            }

            // Password

            if(password.isEmpty()){
                passwordLogin.setError("Enter Password");
                passwordLogin.requestFocus();
                return;
            }

            Snackbar.make(view, "Login Successful",Snackbar.LENGTH_SHORT).setAction("Ok", v -> {
                Toast.makeText(MainActivity.this, "Welcome Back",Toast.LENGTH_SHORT).show();
            }).show();
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}