package com.btl.tracnghiem.ui.TabLayOut;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.btl.tracnghiem.AnswerActivity;
import com.btl.tracnghiem.MainActivity;
import com.btl.tracnghiem.R;
import com.btl.tracnghiem.adapter.AnswerAdapter;
import com.btl.tracnghiem.model.Question;
import com.btl.tracnghiem.ui.viewpaper.ScreenSlidePagerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnswerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AnswerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnswerFragment newInstance(String param1, String param2) {
        AnswerFragment fragment = new AnswerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private ArrayList<Question> answerArrayList;
    private List<Integer> listIndexAnswer;
    private ArrayList<Question> questionArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        answerArrayList= AnswerActivity.answerArrayList;
        listIndexAnswer=AnswerActivity.listIndexAnswer;
        questionArrayList=AnswerActivity.questionArrayList;
        long timer=ScreenSlidePagerActivity.timer;
        Boolean checkAnswer=AnswerActivity.checkAnswer;
        View view= inflater.inflate(R.layout.fragment_answer, container, false);
        GridView gv_answer=(GridView)view.findViewById(R.id.gv_answer);
        AnswerAdapter answerAdapter=new AnswerAdapter(getContext(),R.layout.item_gridview_answer,answerArrayList,listIndexAnswer,checkAnswer);
        gv_answer.setAdapter(answerAdapter);

        gv_answer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(getContext(), ScreenSlidePagerActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                position=listIndexAnswer.get(position);
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("arr",questionArrayList);
                bundle.putInt("position",position);
                bundle.putLong("time",timer);
                bundle.putBoolean("checkAnswer",checkAnswer);
                intent.putExtra("EXTRA_POSITION",bundle);
                startActivity(intent);
                (getActivity()).overridePendingTransition(R.transition.slide_in_left,R.transition.stay);
            }
        });
        return view;
    }

}