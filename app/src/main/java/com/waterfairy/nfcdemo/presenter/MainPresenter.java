package com.waterfairy.nfcdemo.presenter;

import android.app.Activity;

import com.waterfairy.nfcdemo.activity.MainActivity;
import com.waterfairy.nfcdemo.model.MainModel;
import com.waterfairy.nfcdemo.view.MainView;

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
}
