/*
 * Copyright (c) 16. 12. 16 오후 7:47. buckle Lab. All Rights Reserved.
 * Create by rhino Hae Seong Park
 *
 */

package com.skapp.lj.mates;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(),3000);
    }
    private class splashhandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), MainActivity.class)); // 로딩이 끝난후 이동할 Activity
            IntroActivity.this.finish(); // 로딩페이지 Activity Stack에서 제거
        }
    }
}
