package com.waterfairy.tools;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by water_fairy on 2017/7/20.
 * 995637517@qq.com
 */

public class ResSizeUtils {
    public static int getActionBarHeight(Context context) {
        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }

    public static int getResSize(Context context, String type) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier(type, "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getStatusBarHeight(Context context) {
        return getResSize(context, "status_bar_height");
    }
}
