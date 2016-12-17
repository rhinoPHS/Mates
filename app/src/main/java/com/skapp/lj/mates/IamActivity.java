package com.skapp.lj.mates;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class IamActivity extends BaseActivity {

    RadioButton mStudent, mNomal,mMale,mFemale;
    int age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iam);
        mtextView.setText("I am...");


        NumberPicker numberPicker = (NumberPicker)findViewById(R.id.numberPicker);
        numberPicker.setMinValue(20);
        numberPicker.setMaxValue(40);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setDividerPadding(150);

        int color = ContextCompat.getColor(this,R.color.colorText);
        setDividerColor(numberPicker,color);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                age = numberPicker.getValue();
            }
        });

        mStudent = (RadioButton)findViewById(R.id.rb_student);
        mNomal = (RadioButton)findViewById(R.id.rb_nomal);
        mMale = (RadioButton)findViewById(R.id.rb_male);
        mFemale = (RadioButton)findViewById(R.id.rb_female);



//        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
//        seekBar.setProgress (20);
//        seekBar.setOnSeekBarChangeListener(
//                new SeekBar.OnSeekBarChangeListener () {
//                    @Override
//                    public void onProgressChanged(SeekBar seekBar,
//                                                  int progress, boolean fromUser) {
//                        printSelected(progress);
//                    }
//
//                    @Override
//                    public void onStartTrackingTouch(SeekBar seekBar) {
//                    }
//
//                    @Override
//                    public void onStopTrackingTouch(SeekBar seekBar) {
//                        doAfterTrack();
//                    }
//                });
    }

//    public void printSelected(int value){
//        TextView tv = (TextView)findViewById(R.id.textView);
//        tv.setText(String.valueOf(value));
//    }
//
//
//
//    private void doAfterTrack(){
//        TextView tv = (TextView)findViewById(R.id.textView);
//        tv.setText(tv.getText());
//    }
    public void onClick(View view)

    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        U.getInstance().log(""+age);
        U.getInstance().log("I am 익명확인 "+user.getEmail());

        if( (mStudent.isChecked() || mNomal.isChecked()) &&
                (mFemale.isChecked() || mMale.isChecked()))
        {
            Intent intent = new Intent(this,QuestionActivity.class);
            startActivity(intent);
        }
        else{
            if(!mStudent.isChecked() && !mNomal.isChecked()) {
                Toast.makeText(this, "타입은 선택해주세요", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "성별을 선택해주세요", Toast.LENGTH_SHORT).show();
        }

    }
    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


}
