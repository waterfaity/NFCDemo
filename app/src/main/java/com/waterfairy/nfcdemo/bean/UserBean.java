package com.waterfairy.nfcdemo.bean;

import android.text.TextUtils;

/**
 * Created by water_fairy on 2017/7/19.
 * 995637517@qq.com
 */

public class UserBean {
    private long userId = 9;
    private String token;
    private int subject;//学科

    public void setToken(String token) {
        this.token = token;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserIdStr() {
        return userId + "";
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return TextUtils.isEmpty(token) ? "" : token;
    }
}
