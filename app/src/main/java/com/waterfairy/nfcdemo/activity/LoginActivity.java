package com.waterfairy.nfcdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.presenter.LoginPresenter;
import com.waterfairy.nfcdemo.utils.ShareUtils;
import com.waterfairy.nfcdemo.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private LoginPresenter mPresenter;
    @BindView(R.id.et_account)
    EditText mETAccount;
    @BindView(R.id.et_password)
    EditText mETPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        String account = ShareUtils.getAccount(this);
        if (!TextUtils.isEmpty(account)) {
            mETAccount.setText(account);
            mETPassword.setText(ShareUtils.getPassword(this));
        }

    }

    private void initData() {
        mPresenter = new LoginPresenter(this);
    }

    public void login(View view) {
        mPresenter.login(mETAccount.getText().toString(), mETPassword.getText().toString());
    }
}
