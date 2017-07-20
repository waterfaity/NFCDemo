package com.xueduoduo.http.response;


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
    private int pageSize;
    private int totalPage;
    private int totalRow;
    private int pageNumber;
    private String filePath;
    private String fileName;

//    public UserModule getRecord() {
//        return record;
//    }

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
