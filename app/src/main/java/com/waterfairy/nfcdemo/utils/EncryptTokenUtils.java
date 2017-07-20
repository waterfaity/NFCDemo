package com.waterfairy.nfcdemo.utils;

import android.text.TextUtils;

import com.xueduoduo.application.MyApp;
import com.waterfairy.nfcdemo.bean.UserBean;
import com.waterfairy.tools.MD5Utils;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by water_fairy on 2017/6/27.
 * 995637517@qq.com
 */

public class EncryptTokenUtils {
    public static String getToken(HashMap<String, String> tempParams) {
        UserBean mUserModule = MyApp.getInstance()
                .getUserInfo();
        Object[] key_arr = tempParams.keySet().toArray();
        Arrays.sort(key_arr);
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append((mUserModule.getToken()));
        for (Object key : key_arr) {
            stringBuffer.append(key);
            Object value = tempParams.get(key);
            stringBuffer.append(value);
        }
        String tokenSecret = MD5Utils.getMD5Code(stringBuffer.toString());

        return TextUtils.isEmpty(tokenSecret) ? "" : tokenSecret.subSequence(8, 24).toString();
    }
}
