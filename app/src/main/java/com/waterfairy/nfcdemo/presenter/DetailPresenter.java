package com.waterfairy.nfcdemo.presenter;

import android.app.Activity;

import com.waterfairy.nfcdemo.activity.EvaluationDetailActivity;
import com.waterfairy.nfcdemo.database.EvaluationDB;
import com.waterfairy.nfcdemo.model.DetailModel;
import com.waterfairy.nfcdemo.view.DetailView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by water_fairy on 2017/7/21.
 * 995637517@qq.com
 */

public class DetailPresenter implements DetailPresenterListener {
    private Activity mActivity;
    private DetailModel mModel;
    private DetailView mView;

    public DetailPresenter(EvaluationDetailActivity activity) {
        this.mActivity = activity;
        this.mView = activity;
        mModel = new DetailModel(mActivity, this);
    }

    public void getEvaluateData(long studentId, long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(new Date(time));
        try {
            time = format.parse(format1).getTime() + 24 * 60 * 60 * 1000;
            mModel.getDetail(studentId, time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGetData(List<EvaluationDB> evaluationDBs) {
        mView.displayData(evaluationDBs);
    }
}
