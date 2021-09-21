package com.btl.tracnghiem.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.btl.tracnghiem.R;
import com.btl.tracnghiem.model.Exam;
import com.btl.tracnghiem.model.Question;

import java.util.ArrayList;
import java.util.List;

public class AnswerAdapter extends ArrayAdapter<Question> {
    Context context;
    ArrayList<Question> answers;
    int resource;
    List<Integer> listIndex;
    Boolean checkAnswer;


    public AnswerAdapter(@NonNull Context context, int resource,ArrayList<Question> objects,Boolean checkAnswer) {
        super(context, resource, objects);
        this.context=context;
        answers=objects;
        this.resource=resource;
        this.checkAnswer=checkAnswer;

    }
    public AnswerAdapter(@NonNull Context context, int resource,ArrayList<Question> objects,List<Integer> listIndex,Boolean checkAnswer) {
        super(context, resource, objects);
        this.context=context;
        answers=objects;
        this.resource=resource;
        this.listIndex=listIndex;
        this.checkAnswer=checkAnswer;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        convertView =layoutInflater.inflate(resource,parent,false);

        TextView tvNumAns=(TextView)convertView.findViewById(R.id.tvNumAns);
        RadioGroup radioGroup_result=(RadioGroup)convertView.findViewById(R.id.radGroup_result);
        RadioButton radioA_result=(RadioButton)convertView.findViewById(R.id.radA_result);
        RadioButton radioB_result=(RadioButton)convertView.findViewById(R.id.radB_result);
        RadioButton radioC_result=(RadioButton)convertView.findViewById(R.id.radC_result);
        RadioButton radioD_result=(RadioButton)convertView.findViewById(R.id.radD_result);
        ImageView image_answer=(ImageView)convertView.findViewById(R.id.image_answer);

        Question question=getItem(position);

        if(listIndex!=null){
            tvNumAns.setText("Câu hỏi "+(listIndex.get(position)+1));
            switch (question.getAnswer().toUpperCase()){
                case "A":
                    radioA_result.setChecked(true);
                    break;
                case "B":
                    radioB_result.setChecked(true);
                    break;
                case "C":
                    radioC_result.setChecked(true);
                    break;
                case "D":
                    radioD_result.setChecked(true);
                    break;
            }
        }
        else{
            if(checkAnswer){
                switch (question.getResult().toUpperCase()){
                    case "A":
                        radioA_result.setChecked(true);
                        break;
                    case "B":
                        radioB_result.setChecked(true);
                        break;
                    case "C":
                        radioC_result.setChecked(true);
                        break;
                    case "D":
                        radioD_result.setChecked(true);
                        break;
                }
            }
            else {
                switch (question.getAnswer().toUpperCase()){
                    case "A":
                        radioA_result.setChecked(true);
                        break;
                    case "B":
                        radioB_result.setChecked(true);
                        break;
                    case "C":
                        radioC_result.setChecked(true);
                        break;
                    case "D":
                        radioD_result.setChecked(true);
                        break;
                }
            }
            tvNumAns.setText("Câu hỏi "+(position+1));

        }

        for (int i=0;i<radioGroup_result.getChildCount();i++){
            radioGroup_result.getChildAt(i).setEnabled(false);
        }
        if(checkAnswer){
            if(question.getAnswer().equals("")){
                image_answer.setImageResource(R.drawable.ic_warning);
            }
            else if(!question.getAnswer().toUpperCase().equals(question.getResult().toUpperCase())){
                image_answer.setImageResource(R.drawable.ic_cancel_mark);
            }
            else if(question.getAnswer().toUpperCase().equals(question.getResult().toUpperCase())) {
                image_answer.setImageResource(R.drawable.ic_check);
            }

        }
        else {
            if(question.getAnswer().equals("")){
                image_answer.setImageResource(R.drawable.ic_warning);
            }
        }
        return convertView;
    }

}

