package com.btl.tracnghiem.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.btl.tracnghiem.MainActivity;
import com.btl.tracnghiem.R;
import com.btl.tracnghiem.adapter.HomeAdapter;
import com.btl.tracnghiem.ui.Subject.MathFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    BottomNavigationView bottomNavigationView;
    ImageView imageView_math,imageView_physics,imageView_chemistry;
    RecyclerView recyclerView_home;
    HomeAdapter.RecyclerViewClickListener recyclerViewClickListener;
    List<Integer> images;
    List<String> subjects;
    HomeAdapter homeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView_home=(RecyclerView)root.findViewById(R.id.recycleview_home);
        images=new ArrayList<>();
        images.add(R.drawable.math);
        images.add(R.drawable.physics);
        images.add(R.drawable.chemistry);

        subjects=new ArrayList<>();
        subjects.add("Math");
        subjects.add("Physics");
        subjects.add("Chemistry");

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2, GridLayoutManager.VERTICAL,false);
        recyclerView_home.setLayoutManager(gridLayoutManager);
        recyclerView_home.setHasFixedSize(true);
        setOnClickListener();
        homeAdapter=new HomeAdapter(getContext(),subjects,images,recyclerViewClickListener);
        recyclerView_home.setAdapter(homeAdapter);

        return root;


    }

    private void setOnClickListener() {
        recyclerViewClickListener=new HomeAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int Position) {
                bottomNavigationView=(BottomNavigationView)((MainActivity)getActivity()).findViewById(R.id.bottomNavigationView);
                if(Position==0){
                    bottomNavigationView.setSelectedItemId(R.id.nav_math);
                }
                else if(Position==1){
                    bottomNavigationView.setSelectedItemId(R.id.nav_physical);

                } else if (Position==2) {
                    bottomNavigationView.setSelectedItemId(R.id.nav_chemistry);
                }

            }
        };
    }

}