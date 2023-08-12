package com.example.generalknowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;

public class Login_Page extends AppCompatActivity
{
    EditText mail;
    EditText password;
    Button signIn;
    SignInButton signInGoogle;
    TextView signUp;
    TextView forgotPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mail = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        signUp = findViewById(R.id.SignInButton);
        signInGoogle = findViewById(R.id.GoogleSignInButton);
        signUp = findViewById(R.id.signUpText);
        forgotPassword = findViewById(R.id.ForgotPasswordTextView);

        signIn.setOnClickListener(view ->
        {

        });

        signInGoogle.setOnClickListener(view ->
        {

        });

        signUp.setOnClickListener(view ->
        {
            Intent intent =  new Intent(Login_Page.this, SignUpActivity.class);
            startActivity(intent);
        });

        forgotPassword.setOnClickListener(view ->
        {

        });

    }
}