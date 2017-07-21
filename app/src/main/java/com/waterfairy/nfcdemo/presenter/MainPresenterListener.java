package com.waterfairy.nfcdemo.presenter;

import com.waterfairy.nfcdemo.bean.DisciplineCodeBean;
import com.waterfairy.nfcdemo.bean.StudentBean;

import java.util.List;

/**
 * Created by water_fairy on 2017/7/18.
 * 995637517@qq.com
 */

public interface MainPresenterListener {
    void onGetStudentInfo(StudentBean studentBean, List<DisciplineCodeBean> disciplineCodeBeen);
}
