package com.waterfairy.nfcdemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.adapter.MainAdapter;
import com.waterfairy.nfcdemo.bean.UserBean;
import com.waterfairy.nfcdemo.fragment.DataStatisticsFragment;
import com.waterfairy.nfcdemo.nfc.NFCBean;
import com.waterfairy.nfcdemo.nfc.NFCManger;
import com.waterfairy.nfcdemo.presenter.MainPresenter;
import com.waterfairy.nfcdemo.utils.ShareUtils;
import com.waterfairy.nfcdemo.view.MainView;
import com.xueduoduo.application.MyApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView, NFCManger.OnNFCReadListener, ViewPager.OnPageChangeListener {
    private MainPresenter mPresenter;
    private NFCManger nfcManger;
    @BindView(R.id.user_icon)
    ImageView mHeadIcon;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.check_1)
    TextView mCheck1;
    @BindView(R.id.check_2)
    TextView mCheck2;


    private DataStatisticsFragment dataStatisticsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView() {
        registerForContextMenu(mHeadIcon);
        initFragment();
        initViewPager();
    }

    private void initFragment() {
        dataStatisticsFragment = new DataStatisticsFragment();
    }

    private void initViewPager() {
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
    }

    private void initData() {
        mPresenter = new MainPresenter(this);
        initNfc();
    }

    private void initNfc() {
        nfcManger = NFCManger.getInstance();
        nfcManger.init(this, MainActivity.class);
        nfcManger.onReceive(getIntent(), this);
    }

    @OnClick({R.id.user_icon, R.id.text_evaluate, R.id.text_data})
    public void onUserIconClick(View view) {
        switch (view.getId()) {
            case R.id.user_icon:
                openContextMenu(view);
                break;
            case R.id.text_data:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.text_evaluate:
                mViewPager.setCurrentItem(1);
                break;
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("退出");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ShareUtils.removeUserBeanJson(this);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcManger.onResume(this, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcManger.onPause(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        nfcManger.onReceive(intent, this);
    }


    @Override
    public void onReadNFCInfo(NFCBean nfcBean) {
        if (mPresenter == null) {
            mPresenter = new MainPresenter(this);
            initNfc();
        }
        UserBean userInfo = MyApp.getInstance().getUserInfo();
        if (userInfo.isLogin) {
            mPresenter.getStudentInfoFromNfcCode(nfcBean);
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            mCheck1.setBackgroundColor(Color.YELLOW);
            mCheck2.setBackgroundColor(Color.TRANSPARENT);
        } else {
            mCheck2.setBackgroundColor(Color.YELLOW);
            mCheck1.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
