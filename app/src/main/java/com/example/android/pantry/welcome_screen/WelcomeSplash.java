package com.example.android.pantry.welcome_screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.android.pantry.LoginSignupActivity;
import com.example.android.pantry.PantryActivity;
import com.example.android.pantry.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;

public class WelcomeSplash extends AppIntro {

    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        // Add your slide's fragments here
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(SampleSlide.newInstance(R.layout.intro1));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));

        // OPTIONAL METHODS
        // Override bar/separator color
        //setBarColor(Color.parseColor("#3F51B5"));
        //setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button
        showDoneButton(true);
        showSkipButton(true);

        // Turn vibration on and set intensity
        // NOTE: you will probably need to ask VIBRATE permission in Manifest
        setVibrate(true);
        setVibrateIntensity(30);

        //Adds preset animations

    }

    @Override
    public void onDonePressed() {
        Intent intent = new Intent(WelcomeSplash.this,
                PantryActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onSkipPressed(){
        Intent intent = new Intent(WelcomeSplash.this,
                PantryActivity.class);
        startActivity(intent);
        finish();
    }
}
