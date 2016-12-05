package com.skapp.lj.mates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class IamActivity extends AppCompatActivity {
    SeekBar seekBar;
    private int brightness = 50;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iam);
        setTitle("I am");

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setProgress (20);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener () {
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        printSelected(progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        doAfterTrack();
                    }
                });
    }

    public void printSelected(int value){
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText(String.valueOf(value));
//        setBrightness(value);
    }

    private void setBrightness(int value){
        if (value < 10) value = 10;
        else if (value > 100)  value = 100;

        brightness = value;
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = (float)value / 100;
        lp.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        getWindow().setAttributes(lp);
    }

    private void doAfterTrack(){
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText(tv.getText());
    }
    public void onClick(View view)
    {
        Intent intent = new Intent(this,QuestionActivity.class);
        startActivity(intent);
    }


}
