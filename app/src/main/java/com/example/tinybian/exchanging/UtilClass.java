package com.example.tinybian.exchanging;

import android.content.Context;
import android.util.Log;

/**
 * Created by tinybian on 2015/12/22.
 */
public class UtilClass {

    public static int dpToPx(Context context, float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int)(dpValue * scale + 0.5f);
    }

    public static int pxToDp(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;

        return (int)(pxValue / scale + 0.5f);
    }
}
