package com.waterfairy.nfcdemo.bean;

/**
 * Created by water_fairy on 2017/7/19.
 * 995637517@qq.com
 */

public class UserBean {

    /**
     * mobileStatus : 1
     * createTime : 2014-06-13 14:23:44
     * userPwd : b0419f7c2feb3815
     * userSource : 后台导入
     * userEngName :
     * userType : teacher
     * token : 0299F942C3044377699196B93EE181F2
     * userId : 2
     * userLevel : 1
     * userName : 老师1
     * signature : 天行健，君子当自强不息1
     * qq : 76445668
     * roleId : 11
     * regionId : 310101
     * userCode : teacher
     * updateTime : 2017-06-07 15:52:36
     * logoUrl : http://h.xueduoduo.com.cn/data5/image/2017/06/02/214052274314356.png
     * userNickName : 学多宝
     * emailStatus : 1
     * email : ruihu0710@163.com
     * userSex : 女
     * schoolId : 226
     * mobile : 18930661131
     */

    public UserBean(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isLogin;
    private int mobileStatus;
    private String createTime;
    private String userPwd;
    private String userSource;
    private String userEngName;
    private String userType;
    private String token;
    private long userId;
    private String userLevel;
    private String userName;
    private String signature;
    private String qq;
    private long roleId;
    private long regionId;
    private String userCode;
    private String updateTime;
    private String logoUrl;
    private String userNickName;
    private int emailStatus;
    private String email;
    private String userSex;
    private long schoolId;
    private String mobile;

    public int getMobileStatus() {
        return mobileStatus;
    }

    public void setMobileStatus(int mobileStatus) {
        this.mobileStatus = mobileStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserSource() {
        return userSource;
    }

    public void setUserSource(String userSource) {
        this.userSource = userSource;
    }

    public String getUserEngName() {
        return userEngName;
    }

    public void setUserEngName(String userEngName) {
        this.userEngName = userEngName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public int getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(int emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserIdStr() {
        return userId + "";
    }
}
