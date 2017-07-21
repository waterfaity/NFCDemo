package com.waterfairy.nfcdemo.utils;

import com.waterfairy.nfcdemo.bean.ClassInfoBean;
import com.waterfairy.nfcdemo.bean.DisciplineCodeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by water_fairy on 2017/7/21.
 * 995637517@qq.com
 */

public class DataTransUtils {
    public static List<String> transClassInfoToString(List<ClassInfoBean> list) {
        List<String> listString = new ArrayList<>();
        if (list == null || list.size() == 0) {
            listString.add("没有班级");
        } else {
            for (int i = 0; i < list.size(); i++) {
                listString.add(list.get(i).getClassName());
            }
        }
        return listString;
    }

    public static List<String> transSubjectToString(List<DisciplineCodeBean> disciplineCodeBean) {
        List<String> list = new ArrayList<>();
        if (disciplineCodeBean != null) {
            for (int i = 0; i < disciplineCodeBean.size(); i++) {
                list.add(disciplineCodeBean.get(i).getDisciplineName());
            }
        }
        return list;
    }
}