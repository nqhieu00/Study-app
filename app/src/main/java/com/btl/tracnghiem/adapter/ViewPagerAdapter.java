package com.btl.tracnghiem.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.btl.tracnghiem.ui.TabLayOut.AnswerFragment;
import com.btl.tracnghiem.ui.TabLayOut.NoAnswerFragment;
import com.btl.tracnghiem.ui.TabLayOut.ResultFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ResultFragment();
            case 1:
                return new AnswerFragment();
            case 2:
                return new NoAnswerFragment();
            default:
                return new ResultFragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="Tất cả";
                break;
            case 1:
                title="Đã chọn";
                break;
            case 2:
                title="Chưa chọn";
                break;
        }
        return title;
    }
}
