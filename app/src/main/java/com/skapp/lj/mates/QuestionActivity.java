package com.skapp.lj.mates;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnClick;

public class QuestionActivity extends AppCompatActivity {


    private int resultCount;
    private int mProgressStatus = 0;
    private Button resultMates;
    private ArrayList<Question> items;
    private QuestionAdapter questionAdapter;
    private ProgressBar mProgress;
    private Handler mHandler = new Handler();


    @InjectView(R.id.frame)
    SwipeFlingAdapterView flingContainer;

    private Runnable foreTask = new Runnable() {
        @Override
        public void run() {
            mProgress.setProgress(mProgressStatus);
        }
    };

    private Runnable backTask = new Runnable() {
        @Override
        public void run() {
            while(mProgressStatus<=5){
                mProgressStatus = resultCount;
                mProgress.post(foreTask);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questiion);

        resultMates = (Button) findViewById(R.id.resultMates);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);


//        Thread thread = new Thread(backTask);
//        thread.start();  프로그레스바 하면 느려짐...



        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        items = new ArrayList<Question>();

        final Question q1 = new Question("당신의 성별은 무엇입니까?", "남자1", "여자1");
        items.add(q1);
        Question q2 = new Question("당신의 성별은 무엇입니까?", "남자2", "여자2");
        items.add(q2);
        Question q3 = new Question("당신의 성별은 무엇입니까?", "남자3", "여자3");
        items.add(q3);
        Question q4 = new Question("당신의 성별은 무엇입니까?", "남자4", "여자4");
        items.add(q4);
        Question q5 = new Question("당신의 성별은 무엇입니까?", "남자5", "여자5");
        items.add(q5);

        questionAdapter = new QuestionAdapter(this, R.layout.item, items);

        //set the listener and the adapter
        flingContainer.setAdapter(questionAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                ++resultCount;
                Log.d("LIST", "removed object!" + resultCount);
                items.remove(0);
                questionAdapter.notifyDataSetChanged();
                if (resultCount == 5) resultMates.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(QuestionActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(QuestionActivity.this, "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

                // Ask for more data here
//                items.add(q1);
//                questionAdapter.notifyDataSetChanged();
//
//                Log.d("LIST", "notified");

            }


            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(QuestionActivity.this, "Clicked!");
            }
        });
    }
    public void showResultMates(View view)
    {
        Intent intent = new Intent(this,FindingMates.class);
        startActivity(intent);
    }

    static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.right)
    public void right() {
        /**
         * Trigger the right event manually.
         */
        flingContainer.getTopCardListener().selectRight();

    }

    @OnClick(R.id.left)
    public void left() {
        flingContainer.getTopCardListener().selectLeft();
    }
}
