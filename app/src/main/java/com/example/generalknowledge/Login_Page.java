package com.example.generalknowledge;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login_Page extends AppCompatActivity
{
    // Define the things used in xml file
    EditText mail;
    EditText password;
    Button signIn;
    SignInButton signInGoogle;
    TextView signUp;
    TextView forgotPassword;
    ProgressBar progressBarSignIn;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    GoogleSignInClient googleSignInClient;

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        registerActivityForGoogle();

        // Bind the elements from xml
        mail = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        signIn = findViewById(R.id.LogInButton);
        signInGoogle = findViewById(R.id.signInButtonGoogle);
        signUp = findViewById(R.id.NoAccountTextView);
        forgotPassword = findViewById(R.id.ForgotPasswordTextView);
        progressBarSignIn = findViewById(R.id.progressBarSignIn);

        progressBarSignIn.setVisibility(View.INVISIBLE);


        // Listener for sign in button
        // It is using firebase to log-in
        signIn.setOnClickListener(view ->
        {
            String userEmail = mail.getText().toString();
            String userPassword = password.getText().toString();
            signInWithFirebase(userEmail, userPassword);
        });

        // Listener to sign in using google account
        signInGoogle.setOnClickListener(view ->
        {
            signInWithGoogle();
        });

        // Listener to create an account
        signUp.setOnClickListener(view ->
        {
            Intent intent =  new Intent(Login_Page.this, SignUpActivity.class);
            startActivity(intent);
        });

        // Listener for reseting password
        forgotPassword.setOnClickListener(view ->
        {
            Intent intent = new Intent(Login_Page.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }


    /**
     * Sign-in using google account
     * First set up sign in options
     * Tjen start a new intent with the launcher from google
     */
    private void signInWithGoogle()
    {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("921006273982-4oa95gu34c1cpcn1fbs7uggulf4otq6h.apps.googleusercontent.com").requestEmail().build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        Intent intent = googleSignInClient.getSignInIntent();
        activityResultLauncher.launch(intent);

    }

    /**
     * Method to register activity for google sign-in
     */
    public void registerActivityForGoogle()
    {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>()
        {
            @Override
            public void onActivityResult(ActivityResult result)
            {
                int resultCode = result.getResultCode();
                Intent data = result.getData();

                if(resultCode == RESULT_OK && data !=null)
                {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    firebaseSignInWithGoogle(task);
                }
            }


        });
    }

    /**
     * Method to sign-in with google credentials using firebase
     * @param task
     */
    private void firebaseSignInWithGoogle(Task<GoogleSignInAccount> task)
    {
        try
        {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Toast.makeText(this, "Succesfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Login_Page.this, MainActivity.class);
            startActivity(intent);
            finish();

            firebaseGoogleAccount(account);
        }
        catch (ApiException e)
        {
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Method to authenticate with Firebase using Google credentials
     * @param account
     */
    private void firebaseGoogleAccount(GoogleSignInAccount account)
    {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                }
                else
                {

                }
            }
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