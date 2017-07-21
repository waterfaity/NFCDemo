package com.waterfairy.nfcdemo.presenter;

import com.waterfairy.nfcdemo.database.EvaluationDB;

import java.util.List;

/**
 * Created by water_fairy on 2017/7/21.
 * 995637517@qq.com
 */

public interface DetailPresenterListener {
    void onGetData(List<EvaluationDB> evaluationDBs);
}
