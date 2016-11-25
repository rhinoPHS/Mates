package com.skapp.lj.mates;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.rating;

public class SettingActivity extends AppCompatActivity {
    ListView list;
    String[] titles = {
            "수신표시", "계정활성화",};
    Integer[] images = {
            R.drawable.alarm, R.drawable.smile};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        CustomList adapter = new CustomList(this, R.layout.setting_list_item,titles);
        list = (ListView)findViewById(R.id.listV_setting);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), titles[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CustomList extends ArrayAdapter< String > {

        public CustomList(Context context, int resId, String[] items) {
            super(context, resId, items);
        }
        @Override
        public View getView (int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater =
                    (LayoutInflater)getSystemService (Context.LAYOUT_INFLATER_SERVICE);
            // LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate (R.layout.setting_list_item , null, false);

            ImageView imageView = (ImageView)rowView.findViewById(R.id.meesagelist_image);
            TextView title = (TextView)rowView.findViewById(R.id.meesagelist_title);


            title.setText(titles[position]);
            imageView.setImageResource(images [position]);


            return rowView ;
        }
    }
}
