package com.xueduoduo.http.response;


import com.waterfairy.nfcdemo.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by water_fairy on 2017/5/19.
 * 995637517@qq.com
 */

public class BaseResponse<T> {
    private String resultCode;
    //    private UserModule record;
    private String message;
    private ArrayList<T> list;
    private ArrayList<T> studentInfos;
    private ArrayList<T> classList;
    private int pageSize;
    private int totalPage;
    private int totalRow;
    private int pageNumber;
    private String filePath;
    private String fileName;
    private String userType;
    private UserBean user;
    private T record;

    public T getRecord() {
        return record;
    }
    //    public UserModule getRecord() {
//        return record;
//    }


    public ArrayList<T> getClassList() {
        return classList;
    }

    public String getUserType() {
        return userType;
    }

    public UserBean getUser() {
        return user;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public ArrayList<T> getStudentInfos() {
        return studentInfos;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public ArrayList<T> getList() {
        return list;
    }

//    public UserModule getUserModel() {
//        return record;
//    }

    public String getResultCode() {
        return resultCode;
    }

    public String getMessage() {
        return message;
    }
}
