package com.example.chapter03.util;

import android.content.Context;

public class utils {
    // 根据手机的分辨率从 dp unit 转成 px(像素) unit;
    public static int dip2px(Context context, float dpValue){
        // 获取当前手机的像素精度(1个dp对应的px值)
        float scale = context.getResources().getDisplayMetrics().density;
        // 四舍五入取整
        return (int)(dpValue * scale + 0.5f);
    }
}
