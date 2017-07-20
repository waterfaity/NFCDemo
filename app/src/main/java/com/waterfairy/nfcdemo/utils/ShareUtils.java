package com.waterfairy.nfcdemo.utils;

import android.app.Activity;
import android.content.Context;

/**
 * Created by water_fairy on 2017/7/19.
 * 995637517@qq.com
 */

public class ShareUtils {
    public static final String SETTING = "setting";
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";


    public static void saveAccountAndPassword(Context context, String account, String password) {
        context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).
                edit().
                putString(ACCOUNT, account).
                putString(PASSWORD, password).apply();
    }

    public static String getAccount(Context context) {
        return context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).getString(ACCOUNT, "");
    }

    public static String getPassword(Context context) {
        return context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).getString(PASSWORD, "");
    }
}
