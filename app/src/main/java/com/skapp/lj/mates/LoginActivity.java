/*
 * Copyright (c) 16. 12. 16 오후 8:31. buckle Lab. All Rights Reserved.
 * Create by rhino Hae Seong Park
 *
 */

package com.skapp.lj.mates;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mtextView.setText("로그인");
    }
}
