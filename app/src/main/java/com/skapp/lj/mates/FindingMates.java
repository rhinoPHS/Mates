package com.skapp.lj.mates;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class FindingMates extends AppCompatActivity {

    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    String TITLES[] = {"Profile", "Message", "Settings", "Logout", "etc"};
    int ICONS[] = {R.drawable.user24, R.drawable.user24, R.drawable.user24, R.drawable.user24, R.drawable.user24};
    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = "Akash Bangad";
    String EMAIL = "akash.bangad@android4devs.com";
    int PROFILE = R.drawable.avatar;

    private Toolbar toolbar;                              // Declaring the Toolbar Object

    RecyclerView mRecyclerViewNV;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapterNV;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManagerNV;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout
    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggl

    private Spinner spinner_city;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;

    private FrameLayout flContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finding_mates);

        /*NV start*/
        mRecyclerViewNV = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerViewNV.setHasFixedSize(true);
        mAdapterNV = new MyAdapterNV(TITLES, ICONS, NAME, EMAIL, PROFILE);

        mRecyclerViewNV.setAdapter(mAdapterNV);

        mLayoutManagerNV = new LinearLayoutManager(this);
        mRecyclerViewNV.setLayoutManager(mLayoutManagerNV);

        /*navigation drawer ActionBar toggle*/
        Drawer = (DrawerLayout) findViewById(R.id.dl_activity_finding_mates_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        Drawer.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*NV end*/


        /*cardview start*/

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(new MyData("박해성", "안녕하세요", R.drawable.user));
        myDataset.add(new MyData("양수장", "좋은 분 만나요", R.drawable.user));
        myDataset.add(new MyData("김대현", "잘됐으면 좋겠어요", R.drawable.user));

        /*cardview end*/

        /*spinner start*/

        spinner_city = (Spinner) findViewById(R.id.spinner_city);
        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),"position : "+position +parent.getItemAtPosition(position),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /*spinner end*/

        /* 리스트뷰 메뉴 항목 */
        flContainer = (FrameLayout) findViewById(R.id.activity_finding_mates);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
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

            public ViewHolder(View view) {
                super(view);
                mImageView = (ImageView) view.findViewById(R.id.profile_photo);
                mTextView = (TextView) view.findViewById(R.id.textview);
                txtV_say = (TextView) view.findViewById(R.id.txtV_say);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        Toast.makeText(getApplicationContext(), "Positon : " + position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Mate_detail.class);
                        startActivity(intent);
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
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.finding_mates_view, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset.get(position).text);
            holder.mImageView.setImageResource(mDataset.get(position).img);
            holder.txtV_say.setText(mDataset.get(position).txt_say);
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

        public MyData(String text, String txt_say, int img) {
            this.text = text;
            this.txt_say = txt_say;
            this.img = img;
        }
    }
}
