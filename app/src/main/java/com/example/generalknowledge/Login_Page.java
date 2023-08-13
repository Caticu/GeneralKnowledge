package com.example.generalknowledge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Page extends AppCompatActivity
{
    EditText mail;
    EditText password;
    Button signIn;
    SignInButton signInGoogle;
    TextView signUp;
    TextView forgotPassword;
    ProgressBar progressBarSignIn;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mail = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        signIn = findViewById(R.id.LogInButton);
        signInGoogle = findViewById(R.id.signInButtonGoogle);
        signUp = findViewById(R.id.NoAccountTextView);
        forgotPassword = findViewById(R.id.ForgotPasswordTextView);
        progressBarSignIn = findViewById(R.id.progressBarSignIn);

        progressBarSignIn.setVisibility(View.INVISIBLE);



        signIn.setOnClickListener(view ->
        {
            String userEmail = mail.getText().toString();
            String userPassword = password.getText().toString();
            signInWithFirebase(userEmail, userPassword);
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

    /**
     * Sign in with firebase
     * If successful it will take the user to the main activity
     * Otherwise, it will display the user a failed message
     * @param email
     * @param password
     */
    public void signInWithFirebase(String email, String password)
    {
        progressBarSignIn.setVisibility(View.VISIBLE);
        signIn.setClickable(false);

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Intent intent = new Intent(Login_Page.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    progressBarSignIn.setVisibility(View.INVISIBLE);
                    Toast.makeText(Login_Page.this, "Login successful", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Login_Page.this, "Login failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * On start method checks if the user was already sign in
     * If yes it will take the user to the main activity
     */
    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null)
        {
            Intent intent = new Intent(Login_Page.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}