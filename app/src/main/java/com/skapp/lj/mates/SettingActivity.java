package com.skapp.lj.mates;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.rating;
import static android.R.id.list;

public class SettingActivity extends AppCompatActivity {
    int brightness = 50;
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

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar_setting);
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
        TextView tv = (TextView)findViewById(R.id.txtV_setting);
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
        TextView tv = (TextView)findViewById(R.id.txtV_setting);
        tv.setText(tv.getText());
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
