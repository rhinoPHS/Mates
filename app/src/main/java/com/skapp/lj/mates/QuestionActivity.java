package com.skapp.lj.mates;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class QuestionActivity extends BaseActivity {


    private int resultCount;
    private Button resultMates;
    private ArrayList<QuestionClass> items;
    private QuestionAdapter questionAdapter;
    private ProgressBar mProgress;


    @InjectView(R.id.frame)
    SwipeFlingAdapterView flingContainer;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questiion);
        mtextView.setText("Questions");

        resultMates = (Button) findViewById(R.id.resultMates);

        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mProgress.setProgress(0);
        mProgress.setMax(5);


        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        items = new ArrayList<QuestionClass>();

        final QuestionClass q1 = new QuestionClass("당신의 성별은 무엇입니까?", "남자1", "여자1", "1",R.color.colorText);
        items.add(q1);
        QuestionClass q2 = new QuestionClass("당신의 성별은 무엇입니까?", "남자2", "여자2", "2",R.color.colorText2);
        items.add(q2);
        QuestionClass q3 = new QuestionClass("당신의 성별은 무엇입니까?", "남자3", "여자3", "3",R.color.colorText3);
        items.add(q3);
        QuestionClass q4 = new QuestionClass("당신의 성별은 무엇입니까?", "남자4", "여자4", "4",R.color.colorText4);
        items.add(q4);
        QuestionClass q5 = new QuestionClass("당신의 성별은 무엇입니까?", "남자5", "여자5", "5",R.color.colorText5);
        items.add(q5);

        questionAdapter = new QuestionAdapter(this, R.layout.item, items);

        //set the listener and the adapter
        flingContainer.setAdapter(questionAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                ++resultCount;
                mProgress.setProgress(resultCount);
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

    public void showResultMates(View view) {
        Intent intent = new Intent(this, FindingMatesActivity.class);
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
