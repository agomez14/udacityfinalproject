package com.example.android.pantry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

/**
 * Created by elijahstaple on 8/4/15.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Determine whether the current user is an anonymous user
//        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
//            // If user is anonymous, send the user to LoginSignupActivity.class
//            Intent intent = new Intent(MainActivity.this,
//                    LoginSignupActivity.class);
//            startActivity(intent);
//            finish();
//        } else {
            // If current user is NOT anonymous user
            // Get current user data from Parse.com
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {
                // Send logged in users to Welcome.class
                Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Send user to LoginSignupActivity.class
                Intent intent = new Intent(MainActivity.this,
                        LoginSignupActivity.class);
                startActivity(intent);
                finish();
            }
//        }
    }
}
