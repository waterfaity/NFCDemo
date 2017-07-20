package com.xueduoduo.http.request;

import android.content.pm.PackageManager;

import com.xueduoduo.application.MyApp;

import java.util.HashMap;

/**
 * Created by water_fairy on 2017/5/19.
 * 995637517@qq.com
 */

public class PopParamsUtils {
    public final static String AUDIO = "sound";
    public final static String VIDEO = "video";
    public final static String IMAGE = "image";
    public final static String FILE = "file";
    /**
     * 参数名-- 操作者Id
     */
    public final static String OperatorId = "operatorId";
    /**
     * 参数名-- 接口版本号
     */
    public final static String Version = "version";
    /**
     * 参数名-- 调用接口的应用APP类型
     */
    public final static String AppType = "appType";
    /**
     * 参数名--TOKEN
     */
    public final static String Token = "token";
    /**
     * 参数名--客户端版本号
     */
    public final static String ClientVersion = "clientVersion";
    /**
     * 参数名--客户端版包名
     */
    public final static String ClientPackage = "clientPackage";
    /**
     * 参数名--客户端系统版本号
     */
    public final static String SystemVersion = "systemVersion";
    /**
     * 参数值-- Android应用
     */
    public final static String Android = "android";
    /**
     * 参数值-- 接口默认的文件版本号
     */
    public final static String HttpVersionDefaultCode = "1.0";
    /**
     * 应用版本号
     */
    private static String packageVersion = "";
    /**
     * 应用包名
     */
    private static String packageName = "";


    public static HashMap<String, String> getPopParams() {
        //公共参数
        HashMap<String, String> hashMap = new HashMap<>();
        String versionName = "1.0";
        try {
            versionName = MyApp.getPackageInfo().versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        hashMap.put(ClientVersion, versionName);
        hashMap.put(ClientPackage, MyApp.getAppContext().getPackageName());
        hashMap.put(SystemVersion, MyApp.getAndroidVersion());
        hashMap.put(AppType, Android);
        hashMap.put(Version, HttpVersionDefaultCode);
        return hashMap;
    }
}
