package com.skapp.lj.mates;

import android.app.Application;

import com.tsengvn.typekit.Typekit;

/**
 * Created by a on 2016-10-24.
 */

public class CustomFont extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this,"ComfortaaRegular.ttf"))
                .addBold(Typekit.createFromAsset(this,"ComfortaaBold.ttf"));

    }
}
