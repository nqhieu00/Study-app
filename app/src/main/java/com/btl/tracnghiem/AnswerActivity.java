package com.btl.tracnghiem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.btl.tracnghiem.R;
import com.btl.tracnghiem.adapter.AnswerAdapter;
import com.btl.tracnghiem.adapter.ViewPagerAdapter;
import com.btl.tracnghiem.model.Question;
import com.btl.tracnghiem.ui.viewpaper.ScreenSlidePagerActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnswerActivity extends AppCompatActivity {

    public static ArrayList<Question> questionArrayList=new ArrayList<>();
    public static ArrayList<Question> answerArrayList=new ArrayList<>();
    public static ArrayList<Question> noAnswerArrayList=new ArrayList<>();
    public static List<Integer> listIndexAnswer=new ArrayList<>();
    public static List<Integer> listIndexNoAnswer=new ArrayList<>();
    public static boolean checkAnswer=false;

    ViewPager mViewpager;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Đáp án");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                finish();
                overridePendingTransition(R.transition.stay, R.anim.to_right);

            }
        });

        Intent intent=getIntent();

        Bundle bundle=intent.getBundleExtra("Data");
        if (bundle!=null){
            questionArrayList = bundle.getParcelableArrayList("arr");
            checkAnswer=bundle.getBoolean("checkAnswer");

            getData();
        }




        mTabLayout=(TabLayout)findViewById(R.id.tab_layout);
        mViewpager=(ViewPager)findViewById(R.id.viewpager_result);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewpager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewpager);

    }
    private void getData(){
        for (int i=0;i<questionArrayList.size();i++){
            if(!questionArrayList.get(i).getAnswer().equals("")){
                answerArrayList.add(questionArrayList.get(i));
                listIndexAnswer.add(i);
            }
            else {
                noAnswerArrayList.add(questionArrayList.get(i));
                listIndexNoAnswer.add(i);
            }
        }
       
    }

    @Override
    protected void onDestroy() {
        noAnswerArrayList.clear();
        answerArrayList.clear();
        listIndexNoAnswer.clear();
        listIndexAnswer.clear();
        super.onDestroy();
    }

}