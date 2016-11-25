package com.skapp.lj.mates;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MateDetailActivity extends AppCompatActivity {
    private int mSelect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mate_detail);
        this.setTitle("박해성");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mate_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send_message:
                Toast.makeText(this, "메시지보내기", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.report:
                final CharSequence[] items = {"사기꾼","부적절한 내용","더이상사용하지 않는 계정","기타이유"};
                final EditText content = new EditText(this);
                content.setHint("내용을 입력해주세요");
                new AlertDialog.Builder(MateDetailActivity.this)
                        .setTitle("신고항목을 선택해주세요")
                        .setSingleChoiceItems(items, mSelect, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mSelect = which;
                            }
                        })
                        .setPositiveButton("신고하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),items[mSelect] + "신고완료",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("취소", null)
                        .setView(content)
                        .show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
