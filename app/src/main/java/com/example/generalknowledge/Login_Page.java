package com.example.generalknowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_Page extends AppCompatActivity
{
    EditText mail;
    EditText password;
    Button signIn;
    Button signInGoogle;
    TextView signUp;
    TextView forgotPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mail = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        signIn = findViewById(R.id.SignInButton);
        signInGoogle = findViewById(R.id.GoogleSignInButton);
        signUp = findViewById(R.id.signUpText);
        forgotPassword = findViewById(R.id.ForgotPasswordText);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent =  new Intent(Login_Page.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}