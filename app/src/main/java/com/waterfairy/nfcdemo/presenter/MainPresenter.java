package com.waterfairy.nfcdemo.presenter;

import android.app.Activity;

import com.waterfairy.nfcdemo.activity.MainActivity;
import com.waterfairy.nfcdemo.bean.DisciplineCodeBean;
import com.waterfairy.nfcdemo.bean.StudentBean;
import com.waterfairy.nfcdemo.database.EvaluationDB;
import com.waterfairy.nfcdemo.dialog.EvaluateDialog;
import com.waterfairy.nfcdemo.dialog.NoteDialog;
import com.waterfairy.nfcdemo.model.MainModel;
import com.waterfairy.nfcdemo.nfc.NFCBean;
import com.waterfairy.nfcdemo.view.MainView;
import com.waterfairy.tools.ToastUtils;

import java.util.List;

/**
 * Created by water_fairy on 2017/7/18.
 * 995637517@qq.com
 */

public class MainPresenter implements MainPresenterListener {

    private Activity mActivity;
    private MainModel mModel;
    private MainView mView;

    public MainPresenter(MainActivity mainActivity) {
        this.mActivity = mainActivity;
        this.mView = mainActivity;
        this.mModel = new MainModel(mActivity, this);
    }

    public void getStudentInfoFromNfcCode(NFCBean nfcBean) {
        mModel.queryStudentInfo(nfcBean.getId());
    }

    @Override
    public void onGetStudentInfo(StudentBean studentBean, List<DisciplineCodeBean> disciplineCodeBeen) {
        if (EvaluateDialog.isShow) return;
        new EvaluateDialog(mActivity, studentBean, disciplineCodeBeen).setOnEvaluateListener(new EvaluateDialog.onEvaluateListener() {
            @Override
            public void onEvaluate(EvaluationDB evaluationDB) {
//                new NoteDialog(mActivity, "评价成功").show();
                ToastUtils.show("评价成功");
            }
        }).show();
        EvaluateDialog.isShow = true;
    }
}
