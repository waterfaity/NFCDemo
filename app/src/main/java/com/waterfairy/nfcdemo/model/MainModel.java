package com.waterfairy.nfcdemo.model;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.waterfairy.nfcdemo.bean.DisciplineCodeBean;
import com.waterfairy.nfcdemo.bean.StudentBean;
import com.waterfairy.nfcdemo.presenter.MainPresenterListener;
import com.waterfairy.nfcdemo.presenter.RequestPresenter;
import com.waterfairy.tools.ToastUtils;

import java.util.List;

/**
 * Created by water_fairy on 2017/7/18.
 * 995637517@qq.com
 */

public class MainModel {
    private Activity mActivity;
    private MainPresenterListener mPresenter;

    public MainModel(Activity activity, MainPresenterListener presenter) {
        this.mActivity = activity;
        this.mPresenter = presenter;
    }

    public void queryStudentInfo(String id) {
//        537492152  我的公交卡   1
//        1351421698329476  红色后来的nfc 2163
//        3272686307  第一个nfc 2164

        String userId = "";
        switch (id) {
            case "537492152":
                userId = "1";
                break;
            case "1351421698329476":
                userId = "2163";
                break;
            case "3272686307":
                userId = "2164";
                break;
        }
        if (TextUtils.isEmpty(userId)) {
            ToastUtils.show("未绑定该设备");
        } else {
            new RequestPresenter().requestStudentInfo(userId, new RequestPresenter.OnRequestListener() {

                @Override
                public void onSuccess(Object object) {
                    final StudentBean studentBean = (StudentBean) object;
                    new RequestPresenter().requestTeacherDiscipline(studentBean.getClassId(), new RequestPresenter.OnRequestListener() {
                        @Override
                        public void onSuccess(Object object) {
                            List<DisciplineCodeBean> disciplineCodeBeen= (List<DisciplineCodeBean>) object;
                           mPresenter.onGetStudentInfo(studentBean,disciplineCodeBeen);
                        }

                        @Override
                        public void onError(String message) {

                        }
                    });

                }

                @Override
                public void onError(String message) {

                }
            });
        }

        Log.i("mainModel", "queryStudentInfo: " + id);
    }
}
