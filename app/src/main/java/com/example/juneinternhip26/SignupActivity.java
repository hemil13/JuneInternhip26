package com.example.juneinternhip26;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {

    //Variable
    EditText name_signup, email_signup, contact_signup, password_signup, confirm_password_signup;

    Button signup_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        //connect xml and java

        name_signup = findViewById(R.id.name_signup);
        email_signup = findViewById(R.id.email_signup);
        contact_signup = findViewById(R.id.contact_signup);
        password_signup = findViewById(R.id.password_signup);
        confirm_password_signup = findViewById(R.id.confirm_password_signup);

        //button click

        signup_btn.setOnClickListener(view -> {
            String name = name_signup.getText().toString().trim();
            String email = email_signup.getText().toString().trim();
            String contact = contact_signup.getText().toString().trim();
            String password = password_signup.getText().toString().trim();
            String confirmPassword = confirm_password_signup.getText().toString().trim();

            if (name.isEmpty()){
                name_signup.setError("Enter Name");
                name_signup.requestFocus();
                return;
            }

            if (email.isEmpty()){
                email_signup.setError("Enter an Email");
                email_signup.requestFocus();
                return;
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                email_signup.setError("Invalid Email");
                email_signup.requestFocus();
                return;
            }

            if (contact.isEmpty()){
                contact_signup.setError("Enter Contact Number");
                contact_signup.requestFocus();
                return;
            }

            if (contact.length() != 10) {
                contact_signup.setError("Contact Number Must be 10 Digits");
                contact_signup.requestFocus();
                return;
            }

            if (password.isEmpty()){
                password_signup.setError("Enter Password");
                password_signup.requestFocus();
                return;
            }
            if (password.length() < 6){
                password_signup.setError("Password must be of at least 6 characters");
                password_signup.requestFocus();
                return;
            }
            if (confirmPassword.isEmpty()){
                confirm_password_signup.setError("Confirm Password");
                confirm_password_signup.requestFocus();
                return;
            }
            if(!password.equals(confirmPassword)){
                confirm_password_signup.setError("Password do not match");
                confirm_password_signup.requestFocus();
                return;
            }
        });
    }
}