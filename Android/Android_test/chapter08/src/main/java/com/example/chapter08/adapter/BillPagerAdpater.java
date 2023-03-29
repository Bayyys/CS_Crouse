package com.example.chapter08.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chapter08.fragment.BillFragment;

public class BillPagerAdpater extends FragmentPagerAdapter {


    private final int mYear;

    public BillPagerAdpater(@NonNull FragmentManager fm, int year) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mYear = year;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        int month = position + 1;

        // 得到两位数的月份
        // 9 -> 09 10 -> 10
        String zeroMonth = month < 10 ? "0" + month : String.valueOf(month);

        // 得到 2035-09 形式的月份表达
        String yearMonth = mYear + "-" + zeroMonth;
        Log.d("bay", yearMonth);

        // 传递给 BillFragment
        return BillFragment.newInstance(yearMonth);
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (position + 1) + "月份";
    }
}
