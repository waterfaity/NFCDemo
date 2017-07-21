package com.waterfairy.nfcdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.waterfairy.nfcdemo.bean.UserBean;
import com.waterfairy.tools.JsonUtils;

/**
 * Created by water_fairy on 2017/7/19.
 * 995637517@qq.com
 */

public class ShareUtils {
    public static final String SETTING = "setting";
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String USeR_JSON = "userJson";


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

    public static void saveUserBeanJson(Context context, UserBean user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(USeR_JSON, JsonUtils.objectToJson(user)).apply();
    }

    public static void removeUserBeanJson(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(USeR_JSON, "").apply();
    }

    public static UserBean getUserBean(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String string = sharedPreferences.getString(USeR_JSON, "");
        if (TextUtils.isEmpty(string)) {
            return new UserBean(false);
        } else {
            UserBean userBean = new Gson().fromJson(string, UserBean.class);
            userBean.isLogin = true;
            return userBean;
        }
    }
}
