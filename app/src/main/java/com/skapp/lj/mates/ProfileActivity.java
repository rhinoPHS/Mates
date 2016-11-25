package com.skapp.lj.mates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    TextView txtV_ProfileName;
    TextView txtV_UserName;
    Button btn_ProfileUpdate;
    Button btn_QuestionUpdate;
    Button btn_RegionUpdate;
    CircleImageView cv_ProfilePhoto;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtV_ProfileName = (TextView) findViewById(R.id.txtV_ProfileName);
        txtV_ProfileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "프로필명.", Toast.LENGTH_SHORT).show();
            }
        });

        txtV_UserName = (TextView)findViewById(R.id.txtV_UserName);
        txtV_UserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "이름", Toast.LENGTH_SHORT).show();
            }
        });

        btn_ProfileUpdate = (Button)findViewById(R.id.btn_ProfileUpdate);
        btn_ProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),ProfileUpdateActivity.class);
                startActivity(intent);
            }
        });

        btn_QuestionUpdate = (Button)findViewById(R.id.btn_QuestionUpdate);
        btn_QuestionUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),QuestionActivity.class);
                startActivity(intent);
            }
        });

        btn_RegionUpdate = (Button)findViewById(R.id.btn_RegionUpdate);
        btn_RegionUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "지역수정", Toast.LENGTH_SHORT).show();
            }
        });

        cv_ProfilePhoto = (CircleImageView)findViewById(R.id.cv_ProfilePhoto);
        cv_ProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "프로필사진", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
