package com.skapp.lj.mates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.R.attr.y;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MessageActivity.MyData> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mRecyclerView = (RecyclerView) findViewById(R.id.message_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new MyData("박해성", "안녕하세요", "10:45 AM",R.drawable.user));
        myDataset.add(new MyData("양수장", "좋은 분 만나요", "9:00 PM",R.drawable.user));
        myDataset.add(new MyData("김대현", "잘됐으면 좋겠어요", "8:55 AM",R.drawable.user));
    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<MyData> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public ImageView mImageView;
            public TextView mTextView;
            public TextView txtV_say;
            public TextView txtV_Messagetime;

            public ViewHolder(View view) {
                super(view);
                mImageView = (ImageView) view.findViewById(R.id.CIV_MessagePhoto);
                mTextView = (TextView) view.findViewById(R.id.txtV_MessageName);
                txtV_say = (TextView) view.findViewById(R.id.txtV_MessageContent);
                txtV_Messagetime = (TextView)view.findViewById(R.id.txtV_Messagetime);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        Toast.makeText(getApplicationContext(), "Positon : " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public MyAdapter(ArrayList<MyData> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_view, parent, false);
            // set the view's size, margins, paddings and layout parameters
            MyAdapter.ViewHolder vh = new MyAdapter.ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset.get(position).text);
            holder.mImageView.setImageResource(mDataset.get(position).img);
            holder.txtV_say.setText(mDataset.get(position).txt_say);
            holder.txtV_Messagetime.setText(mDataset.get(position).texttime);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

    class MyData {
        public String text;
        public String txt_say;
        public int img;
        public String texttime;
        public MyData(String text, String txt_say, String texttime,int img) {
            this.text = text;
            this.txt_say = txt_say;
            this.img = img;
            this.texttime= texttime;
        }
    }
}
