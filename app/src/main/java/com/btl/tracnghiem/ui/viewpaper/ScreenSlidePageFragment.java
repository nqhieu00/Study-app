package com.btl.tracnghiem.ui.viewpaper;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.btl.tracnghiem.R;
import com.btl.tracnghiem.db.DBHelper;
import com.btl.tracnghiem.db.QuestionDao;
import com.btl.tracnghiem.model.Question;

import java.util.ArrayList;


public class ScreenSlidePageFragment extends Fragment {


    ArrayList<Question> questionArrayList;
    QuestionDao questionDao=new QuestionDao(getContext());

    public static final String EXTRA_PAGE="page";
    public static final String EXTRA_CHECKANSWER="check";
    private int mPage;//số trang hiện tại
    TextView tvNum,tvQuestion;
    RadioGroup radioGroup;
    RadioButton radA,radB,radC,radD;
    Boolean checkAnswer=false;


    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionArrayList=new ArrayList<>();
        ScreenSlidePagerActivity screenSlidePagerActivity=(ScreenSlidePagerActivity) getActivity();
        questionArrayList=screenSlidePagerActivity.getQuestionArrayList();
        mPage=getArguments().getInt(EXTRA_PAGE);
        checkAnswer=getArguments().getBoolean(EXTRA_CHECKANSWER);


    }
    public static ScreenSlidePageFragment create(int paperNumber,boolean checkAnswer){
        ScreenSlidePageFragment screenSlidePageFragment=new ScreenSlidePageFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(EXTRA_PAGE,paperNumber);
        bundle.putBoolean(EXTRA_CHECKANSWER,checkAnswer);
        screenSlidePageFragment.setArguments(bundle);
        return screenSlidePageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup= (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        tvNum=(TextView)viewGroup.findViewById(R.id.tvNum);
        tvQuestion=(TextView)viewGroup.findViewById(R.id.tvQuestion);
        radioGroup=(RadioGroup)viewGroup.findViewById(R.id.radGroup);

        radA=(RadioButton)viewGroup.findViewById(R.id.radA);
        radB=(RadioButton)viewGroup.findViewById(R.id.radB);
        radC=(RadioButton)viewGroup.findViewById(R.id.radC);
        radD=(RadioButton)viewGroup.findViewById(R.id.radD);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(radA.isChecked()){
                    questionArrayList.get(mPage).setAnswer("A");
                }
                else if(radB.isChecked()){
                    questionArrayList.get(mPage).setAnswer("B");
                }
                else if(radC.isChecked()){
                    questionArrayList.get(mPage).setAnswer("C");
                }
                else if(radD.isChecked()) {
                    questionArrayList.get(mPage).setAnswer("D");
                }

            }
        });
        return viewGroup;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNum.setText("Câu "+(mPage+1));
        try {
            if(questionArrayList.get(mPage)!=null){
                tvQuestion.setText(questionArrayList.get(mPage).getQuestion());
                radA.setText(questionArrayList.get(mPage).getAns_a());
                radB.setText(questionArrayList.get(mPage).getAns_b());
                radC.setText(questionArrayList.get(mPage).getAns_c());
                radD.setText(questionArrayList.get(mPage).getAns_d());
                switch (questionArrayList.get(mPage).getAnswer()){
                    case "A":{
                        radA.setChecked(true);
                        break;
                    }
                    case "B":{
                        radB.setChecked(true);
                        break;
                    }
                    case "C":{
                        radC.setChecked(true);
                        break;
                    }
                    case "D":{
                        radD.setChecked(true);
                        break;
                    }

                }
                if(checkAnswer){
                    CheckAnswer();
                    DisableRadio();
                }
            }


        }
        catch (Exception e){

        }
    }

    private void DisableRadio() {
        for (int i=0;i<radioGroup.getChildCount();i++){
            radioGroup.getChildAt(i).setEnabled(false);
        }
    }

    public void CheckAnswer(){
        if(questionArrayList.get(mPage).getResult().equals("A".toLowerCase())){
            radA.setBackgroundColor(radA.getResources().getColor(R.color.green));
        }
        else if(questionArrayList.get(mPage).getResult().equals("B".toLowerCase())){
            radB.setBackgroundColor(radB.getResources().getColor(R.color.green));
        }
        else if(questionArrayList.get(mPage).getResult().equals("C".toLowerCase())){
            radC.setBackgroundColor(radC.getResources().getColor(R.color.green));
        }
        else{
            radD.setBackgroundColor(radD.getResources().getColor(R.color.green));
        }

    }
}