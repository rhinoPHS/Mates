package com.skapp.lj.mates;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.google.android.gms.internal.zzsr.*;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mInputMessage;
    private Button mSendMessage;
    private LinearLayout mMessageLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        setTitle("박해성");

        mInputMessage = (EditText)findViewById(R.id.ET_input_message);
        mSendMessage = (Button)findViewById(R.id.btn_send_message);
        mMessageLog = (LinearLayout)findViewById(R.id.LL_message_log);


        mSendMessage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(mSendMessage))
        {
            String inputText = mInputMessage.getText().toString();
            String answer;

            TextView MyMessage = new TextView(this);
            MyMessage.setBackgroundResource(R.drawable.my_message);
            MyMessage.setText(inputText);
            LinearLayout.LayoutParams MyMessagelayout = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            MyMessagelayout.gravity = Gravity.END;
            final int marginSzie = getResources().getDimensionPixelSize(R.dimen.message_margin);
            MyMessagelayout.setMargins(0,marginSzie,0,marginSzie);
            mMessageLog.addView(MyMessage,MyMessagelayout);

            if(inputText.contains("안녕하세요"))
                answer = "안녕하세요";
            else if (inputText.contains("대화 괜찮으세요?"))
                answer = "네네 괜찮아요";
            else
                answer = "네네";

            final TextView OtherMessage = new TextView(this);
            OtherMessage.setBackgroundResource(R.drawable.other_message);
            OtherMessage.setText(answer);
            OtherMessage.setGravity(Gravity.START);

            mInputMessage.setText("");

            TranslateAnimation MyMessageAnimation = new TranslateAnimation(mMessageLog.getWidth(),0,0,0);
            MyMessageAnimation.setDuration(1000);

            MyMessageAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    LinearLayout.LayoutParams OtherMessagelayout = new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                    OtherMessagelayout.gravity = Gravity.START;
                    OtherMessagelayout.setMargins(marginSzie,marginSzie,marginSzie,marginSzie);
                    mMessageLog.addView(OtherMessage,OtherMessagelayout);

                    TranslateAnimation OthermessageAnimation = new TranslateAnimation(-mMessageLog.getWidth(),0,0,0);
                    OthermessageAnimation.setDuration(1000);
                    OtherMessage.setAnimation(OthermessageAnimation);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            MyMessage.startAnimation(MyMessageAnimation);


        }
    }
}