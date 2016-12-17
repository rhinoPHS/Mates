/*
 * Copyright (c) 16. 12. 16 오후 7:47. buckle Lab. All Rights Reserved.
 * Create by rhino Hae Seong Park
 *
 */

package com.skapp.lj.mates;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    // 인증 관련 객체
    FirebaseAuth mAuth;
    // 인증 관련 리스너
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fb 인증 객체 생성
        mAuth = FirebaseAuth.getInstance();
        // fb 인증관련 리스넌 객체 셍성
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // 인증에 관련된 상태가 변경되면 호출
                // 1. 인증 상태가 변경된 유저 객체 리턴
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    U.getInstance().log("로그 아웃 완료");
                } else {
                    U.getInstance().log("[" + user.getUid() + "] 님 로그 인 완료");
                    U.getInstance().log("Main 익명 확인 "+user.getEmail());
                }
                // 상태값 변경

            }
        };
    }

    // 앱이 활성화 되기 직전 호출
    protected void onStart() {
        super.onStart();
        // 인증 관련 리스너 등록
        if (mAuth != null && mAuthListener != null)
            mAuth.addAuthStateListener(mAuthListener);
    }

    // 앱이 비활성화 되기 직전 호출
    protected void onStop() {
        super.onStop();
        // 여러 화면에서 세션을 유지하면서 진행되다가 로그아웃되면 혹은
        // 세션이 종료되는 상황이 비정상적인 상황이 발생되어서 로그 아웃처리를
        // 해야 한다면 해제를 따로 하지 않고 유지하여 발생시 처리한다.
        // 인증 관련 리스너 해제
        if (mAuth != null && mAuthListener != null)
            mAuth.removeAuthStateListener(mAuthListener);
    }

    public void onLoginActivity(View view) {
        intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }


    public void onAnonymousLogin(View view) {
//        onProgress("익명 계정 요청");
        U.getInstance().log("익명 로그인 요청");
        // -> 익명 계정을 요청!!
        // -> 요청이 완료되면 이벤트를 받아서 후속 처리
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        offProgress();
                        if (task.isSuccessful()) {
                            U.getInstance().log("익명 계성 생성 성공");
                            intent = new Intent(MainActivity.this,IamActivity.class);
                            startActivity(intent);
                        } else {
                            U.getInstance().log("익명 계성 생성 실패");
                        }

                    }
                });
    }
}
