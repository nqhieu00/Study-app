package com.btl.tracnghiem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.btl.tracnghiem.R;
import com.btl.tracnghiem.model.Exam;

import java.util.ArrayList;
import java.util.List;

public class ExamAdapter extends ArrayAdapter<Exam> {
    Context context;
    ArrayList<Exam> exams;
    int resource;
    public ExamAdapter(@NonNull Context context, int resource,ArrayList<Exam> objects) {
        super(context, resource, objects);
        this.context=context;
        exams=objects;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        convertView =layoutInflater.inflate(resource,parent,false);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.imageView2);
        TextView textView=(TextView)convertView.findViewById(R.id.tvNumExam);
        Exam exam=getItem(position);
        
        textView.setText("Đề "+exam.getNumExam());
        imageView.setImageResource(R.drawable.ic_book);



        return convertView;
    }
}
