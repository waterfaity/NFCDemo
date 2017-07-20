package com.waterfairy.nfcdemo.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;

import com.waterfairy.nfcdemo.activity.LoginActivity;
import com.waterfairy.nfcdemo.activity.MainActivity;
import com.waterfairy.nfcdemo.model.LoginModel;
import com.waterfairy.nfcdemo.view.LoginView;
import com.waterfairy.tools.ToastUtils;

/**
 * Created by water_fairy on 2017/7/18.
 * 995637517@qq.com
 */

public class LoginPresenter implements LoginPresenterListener {
    private Activity mActivity;
    private LoginView mView;
    private LoginModel mLoginModel;

    public LoginPresenter(LoginActivity loginActivity) {
        this.mActivity = loginActivity;
        this.mView = loginActivity;
        this.mLoginModel = new LoginModel(loginActivity, this);
    }


    public void login(String account, String password) {
        if (TextUtils.isEmpty(account)) {
            ToastUtils.show("请输入帐号");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.show("请输入密码");
            return;
        }
        mLoginModel.requestLogin(account, password);
    }

    @Override
    public void onLoginSuccess() {
        mActivity.startActivity(new Intent(mActivity, MainActivity.class));
        mActivity.finish();
    }
}
