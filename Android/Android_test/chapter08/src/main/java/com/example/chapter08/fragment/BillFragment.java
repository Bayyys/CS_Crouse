package com.example.chapter08.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.chapter08.R;
import com.example.chapter08.adapter.BillListAdapter;
import com.example.chapter08.database.BillDBHelper;
import com.example.chapter08.entity.BillInfo;

import java.util.List;

public class BillFragment extends Fragment {

    public static BillFragment newInstance(String yearMonth) {
        BillFragment fragment = new BillFragment();
        Bundle args = new Bundle();
        args.putString("yearMonth", yearMonth);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // "2012-05"
        // 获取布局中的fragment_bill视图
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        // 从数据库中查询指定月份的账单
        ListView lv_bill = view.findViewById(R.id.lv_bill);
        BillDBHelper mDBHelper = BillDBHelper.getInstance(getContext());
        Bundle arguments = getArguments();
        String yearMonth = arguments.getString("yearMonth");

        // 查询指定月份的账单
        List<BillInfo> billInfoList = mDBHelper.queryByMonth(yearMonth);

        // 显示账单列表
        BillListAdapter adapter = new BillListAdapter(getContext(), billInfoList);
        lv_bill.setAdapter(adapter);
        return view;
    }
}