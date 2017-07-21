package com.waterfairy.nfcdemo.bean;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by water_fairy on 2017/7/20.
 * 995637517@qq.com
 */

public class StudentBean implements Serializable {
    private static final long serialVersionUID = 201707211403L;

    /**
     * userId : 1
     * userSex : 男
     * userName : 系统管理员
     * logoUrl : http://h.xueduoduo.com.cn/data3/headLogo/2014/07/12/20141412071431qpOA2V.jpg
     */

    private long userId;
    private String userName;
    private String logoUrl;
    private String gradeName;
    private long classId;
//
//    {
//        "gradeName":"二年级", "classId":51, "userId":2163, "userSex":"女", "grade":2, "userName":
//        "妞妞是我", "className":"测试一（1）班",
//            "logoUrl":
//        "http://h.xueduoduo.com.cn/data4/image/2017/06/29/141311356142833.jpg", "schoolId":
//        226, "signature":"下自成蹊"

    private String userSex;
    private String className;
    private int grade;
    private long schoolId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
