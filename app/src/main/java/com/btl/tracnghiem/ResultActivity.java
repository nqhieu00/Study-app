package com.btl.tracnghiem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.btl.tracnghiem.adapter.AnswerAdapter;
import com.btl.tracnghiem.model.Question;
import com.btl.tracnghiem.ui.viewpaper.ScreenSlidePageFragment;
import com.btl.tracnghiem.ui.viewpaper.ScreenSlidePagerActivity;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {


    ArrayList<Question> questionArrayList = new ArrayList<>();
    Long timer ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Đáp án");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                overridePendingTransition(R.transition.slide_in_right,R.transition.stay);
                finish();

            }
        });


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Data");
        if(bundle!=null){
            questionArrayList = bundle.getParcelableArrayList("arr");
            timer = bundle.getLong("time");
        }
        TextView tv_result = (TextView) findViewById(R.id.tv_result);
        int score = 0;
        for (Question q : questionArrayList) {
            if (q.getAnswer().toLowerCase().equals(q.getResult().toLowerCase())) {
                score++;
            }
        }

        tv_result.setText("Kết quả: " + score + "/" + questionArrayList.size());


        Button btn_result = (Button) findViewById(R.id.btn_result);
        Button btn_cont = (Button) findViewById(R.id.btn_continue);
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, AnswerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("arr", questionArrayList);
                bundle.putLong("time", timer);
                bundle.putBoolean("checkAnswer",true);
                intent.putExtra("Data", bundle);
                startActivity(intent);
                overridePendingTransition(R.transition.slide_in_left,R.transition.stay);

            }
        });
        btn_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ScreenSlidePagerActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                Bundle bundle = new Bundle();
                bundle.putString("EXTRA_SUBJECT", "a");
                bundle.putInt("EXTRA_EXAM", 2);
                intent.putExtra("Data", bundle);
                startActivity(intent);
                overridePendingTransition(R.transition.slide_in_left,R.transition.stay);


            }
        });

    }
}