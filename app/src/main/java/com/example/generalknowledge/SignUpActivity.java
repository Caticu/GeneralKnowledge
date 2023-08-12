package com.example.generalknowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity
{
    EditText name;
    EditText email;
    EditText password;
    Button createAccountButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.NameBox);
        email = findViewById(R.id.EmailBox);
        password = findViewById(R.id.PasswordBox);
        createAccountButton = findViewById(R.id.CreateAccountButton);
        progressBar = findViewById(R.id.progressBar);

        createAccountButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });
    }
}