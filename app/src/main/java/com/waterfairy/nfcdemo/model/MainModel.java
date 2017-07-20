package com.waterfairy.nfcdemo.model;

import android.app.Activity;

import com.waterfairy.nfcdemo.presenter.MainPresenterListener;

/**
 * Created by water_fairy on 2017/7/18.
 * 995637517@qq.com
 */

public class MainModel {
    private Activity mActivity;
    private MainPresenterListener mPresenter;

    public MainModel(Activity activity, MainPresenterListener presenter) {
        this.mActivity =activity;
        this.mPresenter=presenter;
    }
}
