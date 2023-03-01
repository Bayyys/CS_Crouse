package com.example.chapter09.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.chapter09.BroadOrderActivity;

public class OrderBReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction().equals(BroadOrderActivity.ORDER_ACTION)){
            Log.d("bay", "接收器B收到一个有序广播");
            abortBroadcast(); // 中断广播，此时后面的接收器无法收到该广播
        }
    }
}
