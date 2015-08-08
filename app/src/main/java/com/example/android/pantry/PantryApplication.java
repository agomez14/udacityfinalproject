package com.example.android.pantry;

/**
 * Created by elijahstaple on 8/8/15.
 */

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class PantryApplication extends Application {

    public ParseUser user;

    @Override
    public void onCreate() {
        super.onCreate();

//        ParseObject.registerSubclass(com.parse.loginsample.basic.Event.class);
//        ParseUser.registerSubclass(com.parse.loginsample.basic.User.class);

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "OgR0d02uqebhqJN8PfKofwsGZLtC6HQMk0zpSapK", "PCMJy2hyh6FKPmZBqlCi7LcKutJqmaHwtMY5OpYY");

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        //ParseFacebookUtils.initialize(this);

        user = ParseUser.getCurrentUser();
    }
}
