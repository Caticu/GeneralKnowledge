package com.example.generalknowledge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LandingActivity extends AppCompatActivity {

    ImageView image;
    TextView text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        // Find the image and text view from the xml file
        image = findViewById(R.id.LandingPageImg);
        text = findViewById(R.id.LandingPageText);

        // Bind the animation to the view
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.landing_page_anim);

        text.startAnimation(animation);

        // The animation will be 4 seconds, wait 1 sec and then execute the code to open the main activity
        new Handler().postDelayed(() -> {
           Intent intent = new Intent(LandingActivity.this,Login_Page.class);
           startActivity(intent);
           finish();
        }, 5000);

    }
}