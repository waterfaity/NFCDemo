package com.waterfairy.nfcdemo.model;

import android.app.Activity;
import android.database.sqlite.SQLiteException;

import com.waterfairy.nfcdemo.database.EvaluationDB;
import com.waterfairy.nfcdemo.database.EvaluationDBDao;
import com.waterfairy.nfcdemo.presenter.DetailPresenter;
import com.waterfairy.nfcdemo.presenter.DetailPresenterListener;
import com.waterfairy.nfcdemo.presenter.MainPresenter;
import com.xueduoduo.application.MyApp;

import java.util.List;

/**
 * Created by water_fairy on 2017/7/21.
 * 995637517@qq.com
 */

public class DetailModel {
    private Activity mActivity;
    private DetailPresenterListener mPresenter;

    public DetailModel(Activity activity, DetailPresenterListener detailPresenterListener) {
        this.mActivity = activity;
        this.mPresenter = detailPresenterListener;
    }

    public void getDetail(long studentId, long time) {
        long userId = MyApp.getInstance().getUserInfo().getUserId();
        EvaluationDBDao evaluationDBDao = MyApp.getInstance().getDaoSession().getEvaluationDBDao();
        List<EvaluationDB> evaluationDBs = null;
        try {
            evaluationDBs = evaluationDBDao.queryRaw("where STUDENT_ID= " + studentId + " and TEACHER_ID = " + userId + " and TIME <= " + time+" order by TIME desc");
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        mPresenter.onGetData(evaluationDBs);
    }
}
