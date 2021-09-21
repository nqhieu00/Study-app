package com.btl.tracnghiem.ui.viewpaper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.telecom.Call;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.btl.tracnghiem.AnswerActivity;
import com.btl.tracnghiem.R;
import com.btl.tracnghiem.adapter.AnswerAdapter;
import com.btl.tracnghiem.db.QuestionDao;
import com.btl.tracnghiem.model.Question;
import com.btl.tracnghiem.ResultActivity;

import java.util.ArrayList;

public class ScreenSlidePagerActivity extends FragmentActivity{
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private int NUM_PAGES ;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager2 viewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    public static final int RESQUEST_CODE=2021;
    FragmentStateAdapter pagerAdapter;
    ArrayList<Question> questionArrayList=new ArrayList<>();
    QuestionDao questionDao;
    TextView tvKiemtra,tvTimer;
    ImageView imageListAnswer;
    Boolean checkAnswer=false;
    CountDownTimer countDownTimer;
    String Subject="";
    int Exam;
    final long time=3000000;
    public static long timer;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RESQUEST_CODE){
           if(resultCode==RESULT_OK){
              checkAnswer=true;
              viewPager.setAdapter(pagerAdapter);
           }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sctivity_screen_slide);
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());



        questionDao=new QuestionDao(this);
        getDataSubject();
        questionArrayList=questionDao.getQuestion(Subject,Exam);
        NUM_PAGES=questionArrayList.size();
        timer=time;
        getDataFromAnswer();

        tvTimer=(TextView)findViewById(R.id.tvTimer);

        countDownTimer=new CountDownTimer(timer, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long Minutes = millisUntilFinished / (60 * 1000) % 60;
                long Seconds = millisUntilFinished / 1000 % 60;
                String time=String.format("%02d:%02d",Minutes,Seconds);
                if (!checkAnswer){
                    timer=millisUntilFinished;
                }

                tvTimer.setText(time);

            }

            @Override
            public void onFinish() {
                tvTimer.setText("Hết giờ");
            }


        }.start();
        if(checkAnswer){
            countDownTimer.cancel();
            getTime(timer);

        }

        tvKiemtra=(TextView)findViewById(R.id.tvKiemTra);
        tvKiemtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countDownTimer.cancel();
                checkAnswer=true;
                Intent intent=new Intent(getBaseContext(), ResultActivity.class);
                Bundle bundle=new Bundle();
                ArrayList<String> Answers=new ArrayList<>();
                for (Question q:questionArrayList){
                    Answers.add(q.getAnswer());
                }

                bundle.putParcelableArrayList("arr",questionArrayList);
                bundle.putLong("time",timer);
                intent.putExtra("Data",bundle);

                startActivityForResult(intent,RESQUEST_CODE);

            }
        });
        imageListAnswer =(ImageView)findViewById(R.id.image_list_answer);
        imageListAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ScreenSlidePagerActivity.this, AnswerActivity.class);
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("arr", questionArrayList);
                bundle.putBoolean("checkAnswer",checkAnswer);
                intent.putExtra("Data",bundle);
                startActivity(intent);
                overridePendingTransition(R.transition.slide_in_left,R.transition.stay);
            }
        });

        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back();
            }
        });

    }

    private void getDataFromAnswer() {
        Intent intent=getIntent();
        Bundle bundle_Answer=intent.getBundleExtra("EXTRA_POSITION");
        if(bundle_Answer!=null){
            Subject="a";
            Exam=2;
            int position=0;
            position=bundle_Answer.getInt("position");
            timer=bundle_Answer.getLong("time");
            checkAnswer=bundle_Answer.getBoolean("checkAnswer");
            questionArrayList=bundle_Answer.getParcelableArrayList("arr");
            NUM_PAGES=questionArrayList.size();
            viewPager.setCurrentItem(position,false);
        }
    }

    private void getTime(long time) {
        long millisUntilFinished=time;
        long Minutes = millisUntilFinished / (60 * 1000) % 60;
        long Seconds = millisUntilFinished / 1000 % 60;
        tvTimer.setText(String.format("%02d:%02d",Minutes,Seconds));
    }

    private void getDataSubject() {

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("Data");

        if(bundle!=null){
            Subject=bundle.getString("EXTRA_SUBJECT");
            Exam=bundle.getInt("EXTRA_EXAM");
        }
    }


    void Back(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Bạn có chắc chắn muốn thoát ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
    public ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);

        }
    }



    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @Override
        public Fragment createFragment(int position) {

            return ScreenSlidePageFragment.create(position,checkAnswer);
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
    public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}