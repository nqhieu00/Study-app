package com.btl.tracnghiem.ui.Subject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;

import com.btl.tracnghiem.MainActivity;
import com.btl.tracnghiem.R;
import com.btl.tracnghiem.adapter.ExamAdapter;
import com.btl.tracnghiem.db.QuestionDao;
import com.btl.tracnghiem.model.Exam;
import com.btl.tracnghiem.ui.viewpaper.ScreenSlidePagerActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class chemistryFragment extends Fragment implements AdapterView.OnItemClickListener {



    ExamAdapter examAdapter;
    GridView gridView;
    ArrayList<Exam> examArrayList=new ArrayList<>();


    public chemistryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmenthóa


        View root =inflater.inflate(R.layout.fragment_chemistry, container, false);
        gridView=(GridView)root.findViewById(R.id.gv_subject_chemistry);
        QuestionDao questionDao=new QuestionDao(getContext());
        examArrayList=questionDao.getExam("a");
        examAdapter=new ExamAdapter(getContext(),R.layout.item_gridview,examArrayList);
        gridView.setAdapter(examAdapter);
        gridView.setOnItemClickListener(this);

        return root;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getContext(), ScreenSlidePagerActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("EXTRA_SUBJECT","a");
        bundle.putInt("EXTRA_EXAM",2);
        intent.putExtra("Data",bundle);
        startActivity(intent);
        //(getActivity()).overridePendingTransition(R.transition.slide_in_left,R.transition.stay);
    }


}