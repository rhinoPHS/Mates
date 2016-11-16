package com.skapp.lj.mates;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by a on 2016-11-08.
 */

class QuestionAdapter extends BaseAdapter {
    Context context;
    LayoutInflater Inflater;
    ArrayList<Question> arrQuestion;
    int layout;

    public QuestionAdapter(Context context, int alayout, ArrayList<Question> aarrQuestion) {
        this.context = context;
        Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrQuestion = aarrQuestion;
        layout = alayout;
    }

    public int getCount() {
        return arrQuestion.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final  int pos = position;
        if(convertView == null){
            convertView = Inflater.inflate(layout,parent,false);
        }
        TextView questionTitle = (TextView)convertView.findViewById(R.id.questionTitle);
        questionTitle.setText(arrQuestion.get(position).getQuestionTitle());

        Button answerLeft = (Button)convertView.findViewById(R.id.answerLeft);
        answerLeft.setText(arrQuestion.get(position).getAnswerLeft());

        Button answerRight = (Button)convertView.findViewById(R.id.answerRight);
        answerRight.setText(arrQuestion.get(position).getAnswerRight());

        return convertView;
    }

}
