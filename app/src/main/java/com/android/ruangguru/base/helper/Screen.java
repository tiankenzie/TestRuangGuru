package com.android.ruangguru.base.helper;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class Screen {
    public static Integer getWidth(Context context) {
        WindowManager window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        window.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}
