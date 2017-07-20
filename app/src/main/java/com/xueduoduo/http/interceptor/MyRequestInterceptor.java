package com.xueduoduo.http.interceptor;

import android.text.TextUtils;

import com.waterfairy.nfcdemo.utils.EncryptTokenUtils;
import com.xueduoduo.application.MyApp;
import com.xueduoduo.http.request.PopParamsUtils;

import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.MultipartBody;

/**
 * Created by water_fairy on 2017/5/19.
 * 995637517@qq.com
 */

public class MyRequestInterceptor extends RequestInterceptor {
    @Override
    void putExtraParamsToFromBody(FormBody.Builder builder, HashMap<String, String> tempParams) {
        //个人参数
        String userInfoId = MyApp.getInstance().getUserInfo().getUserIdStr();

        if (addOperatorId(userInfoId, tempParams)) {
            tempParams.put(PopParamsUtils.OperatorId, "-999");
            builder.add(PopParamsUtils.OperatorId, "-999");
        }
        if (addUserId(userInfoId, tempParams)) {
            tempParams.put("userId", "-999");
            builder.add("userId", "-999");
        }
        String token = EncryptTokenUtils.getToken(tempParams);
        builder.add(PopParamsUtils.Token, token);
    }

    @Override
    void putExtraParamsToMulFormBody(MultipartBody.Builder builder, HashMap<String, String> tempParams) {
        //个人参数
        String userInfoId = MyApp.getInstance().getUserInfo().getUserIdStr();

        if (addOperatorId(userInfoId, tempParams)) {
            tempParams.put(PopParamsUtils.OperatorId, "-999");
            builder.addFormDataPart(PopParamsUtils.OperatorId, "-999");
        }
        if (addUserId(userInfoId, tempParams)) {
            tempParams.put("userId", "-999");
            builder.addFormDataPart("userId", "-999");
        }
        builder.addFormDataPart(PopParamsUtils.Token, EncryptTokenUtils.getToken(tempParams));
    }


    @Override
    void putExtraParamsToUrl(StringBuilder stringBuilder, HashMap<String, String> tempParams) {
        //个人参数
        String userInfoId = MyApp.getInstance().getUserInfo().getUserIdStr();
        if (addOperatorId(userInfoId, tempParams)) {
            tempParams.put(PopParamsUtils.OperatorId, "-999");
            stringBuilder.append("&").append(PopParamsUtils.OperatorId).append("=").append("-999");
        }
        if (addUserId(userInfoId, tempParams)) {
            tempParams.put("userId", "-999");
            stringBuilder.append("&").append("userId").append("=").append("-999");
        }
        stringBuilder.append("&").append(PopParamsUtils.Token).append("=").append(EncryptTokenUtils.getToken(tempParams));
    }

    /**
     * 是否需要添加默认的OperatorId
     *
     * @param userId
     * @param tempParams
     * @return
     */
    private boolean addOperatorId(String userId, HashMap<String, String> tempParams) {
        if (TextUtils.isEmpty(userId) && tempParams.containsKey(PopParamsUtils.OperatorId)) {
            return true;
        }
        return false;
    }

    private boolean addUserId(String userId, HashMap<String, String> tempParams) {
        if (TextUtils.isEmpty(userId)
                && tempParams.containsKey("userId")
                && TextUtils.isEmpty(tempParams.get("userId"))) {
            return true;
        }
        return false;
    }
}
