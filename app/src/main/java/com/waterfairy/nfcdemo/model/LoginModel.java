package com.waterfairy.nfcdemo.model;

import android.app.Activity;

import com.waterfairy.nfcdemo.activity.LoginActivity;
import com.waterfairy.nfcdemo.bean.UserBean;
import com.waterfairy.nfcdemo.presenter.LoginPresenterListener;
import com.waterfairy.nfcdemo.utils.ShareUtils;
import com.waterfairy.tools.ToastUtils;
import com.xueduoduo.http.BaseCallback;
import com.xueduoduo.http.RetrofitRequest;
import com.xueduoduo.http.RetrofitService;
import com.xueduoduo.http.response.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by water_fairy on 2017/7/18.
 * 995637517@qq.com
 */

public class LoginModel {
    private Activity mActivity;
    private LoginPresenterListener mPresenter;

    public LoginModel(LoginActivity activity, LoginPresenterListener presenter) {
        this.mActivity = activity;
        this.mPresenter = presenter;

    }

    public void requestLogin(final String account, final String password) {

        RetrofitService retrofitRequest = RetrofitRequest.getInstance().getNormalRetrofit();
        retrofitRequest.login(account, password).enqueue(new BaseCallback<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                UserBean user = baseResponse.getUser();
                if (user != null) {
                    mPresenter.onLoginSuccess();
                    ShareUtils.saveAccountAndPassword(mActivity, account, password);
                    ShareUtils.saveUserBeanJson(mActivity, user);
                } else {
                    ShareUtils.removeUserBeanJson(mActivity);
                }
            }

            @Override
            public void onFailed(int code, String message) {
                ToastUtils.show(message);
            }
        });

    }
}
