package com.waterfairy.nfcdemo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.waterfairy.nfcdemo.bean.ClassInfoBean;
import com.waterfairy.nfcdemo.dialog.EvaluateDialog;
import com.waterfairy.nfcdemo.dialog.ListDialog;
import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.presenter.RequestPresenter;
import com.waterfairy.nfcdemo.utils.DataTransUtils;
import com.waterfairy.nfcdemo.widget.ProgressView;
import com.waterfairy.nfcdemo.widget.StartView;
import com.waterfairy.tools.ResSizeUtils;
import com.waterfairy.tools.ToastUtils;
import com.xueduoduo.application.MyApp;
import com.xueduoduo.http.BaseCallback;
import com.xueduoduo.http.RetrofitRequest;
import com.xueduoduo.http.RetrofitService;
import com.xueduoduo.http.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by water_fairy on 2017/7/19.
 * 995637517@qq.com
 */

public class DataStatisticsFragment extends android.support.v4.app.Fragment implements ListDialog.OnSpinnerSelectListener {
    private View mRootView;
    @BindView(R.id.progress_1)
    ProgressView progressView1;
    @BindView(R.id.progress_2)
    ProgressView progressView2;
    @BindView(R.id.progress_3)
    ProgressView progressView3;
    @BindView(R.id.progress_4)
    ProgressView progressView4;

    @BindView(R.id.student_grade)
    TextView mTVGrade;
    @BindView(R.id.student_class)
    TextView mTVClass;

    @BindView(R.id.star_english)
    StartView mSVEnglish;
    @BindView(R.id.star_math)
    StartView mSVMath;
    @BindView(R.id.star_chinese)
    StartView mSVChinese;


    private TextView mLastMonth;
    private List<TextView> mMonths;
    private int[] mTVMonthIds = new int[]{
            R.id.month_1, R.id.month_2, R.id.month_3, R.id.month_4,
            R.id.month_5, R.id.month_6, R.id.month_7, R.id.month_8,
            R.id.month_9, R.id.month_10, R.id.month_11, R.id.month_12};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_data_statictics, container, false);
        ButterKnife.bind(this, mRootView);
        initData();
        initView();
        return mRootView;
    }

    private void initView() {
        progressView1.initData(0f);
        progressView2.initData(0f);
        progressView3.initData(0f);
        progressView4.initData(0f);

        mMonths = new ArrayList<>();
        OnMonthClickListener onMonthClickListener = new OnMonthClickListener();
        for (int i = 0; i < 12; i++) {
            TextView month = (TextView) mRootView.findViewById(mTVMonthIds[i]);
            if (i == 0) mLastMonth = month;
            mMonths.add(month);
            month.setTag(i);
            month.setOnClickListener(onMonthClickListener);
        }
    }

    @OnClick(R.id.info)
    public void onInfoClick(View view) {

    }

    @OnClick({R.id.student_grade, R.id.student_class})
    public void onGradeClick(View view) {
        ArrayList<ClassInfoBean> classInfo = MyApp.getInstance().getClassInfo();
        if (classInfo == null) {
            new RequestPresenter().requestClassInfo(new RequestPresenter.OnRequestListener() {
                @Override
                public void onSuccess(Object object) {
                    showClassList((List<ClassInfoBean>) object);
                }

                @Override
                public void onError(String message) {
                    ToastUtils.show(message);
                }
            });

        } else {
            showClassList(classInfo);
        }
    }

    public void showClassList(List<ClassInfoBean> list) {
        int[] gradeCoordinate = getGradeCoordinate(mTVGrade);
        List<String> listStr = DataTransUtils.transClassInfoToString(list);
        ListDialog listDialog = new ListDialog(getActivity(), mTVGrade, ListDialog.TAG_GRADE, gradeCoordinate[0], gradeCoordinate[1], gradeCoordinate[2], listStr, DataStatisticsFragment.this);
        listDialog.show();
    }

    private int[] getGradeCoordinate(View view) {
        View parent1 = (View) mTVGrade.getParent();
        View parent2 = (View) parent1.getParent();
        View parent3 = (View) parent2.getParent();
        View parent4 = (View) parent3.getParent();

        return new int[]{
                view.getLeft() + parent1.getLeft() + parent2.getLeft() + parent3.getLeft() + parent4.getLeft(),
                view.getTop() + parent1.getTop() + parent2.getTop() + parent3.getTop() + parent4.getTop() +
                        view.getBottom() + ResSizeUtils.getStatusBarHeight(getActivity())
                        + ResSizeUtils.getActionBarHeight(getActivity()), view.getRight() - view.getLeft()};
    }

    private void initData() {
        new RequestPresenter().requestClassInfo(new RequestPresenter.OnRequestListener() {
            @Override
            public void onSuccess(Object object) {
                ArrayList<ClassInfoBean> classInfoBeen = (ArrayList<ClassInfoBean>) object;
                if (classInfoBeen != null && classInfoBeen.size() > 0) {
                    mTVGrade.setText(classInfoBeen.get(0).getClassName());
                    requestMonthData(0);
                } else {
                    ToastUtils.show("没有班级");
                }
            }

            @Override
            public void onError(String message) {
                ToastUtils.show(message);
            }
        });
    }

    @Override
    public void onSelect(View view, int tag, int pos) {
        if (tag == ListDialog.TAG_GRADE) {
            mMonths.get(0).performClick();
        }
    }

    private class OnMonthClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v == mLastMonth) return;
            TextView textView = (TextView) v;
            mLastMonth.setTextColor(((TextView) v).getCurrentTextColor());
            mLastMonth.setBackgroundColor(Color.TRANSPARENT);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.drawable.bg_shape_month);
            int pos = (int) v.getTag();
            mLastMonth = (TextView) v;
            requestMonthData(pos);
        }
    }

    private void requestMonthData(int pos) {
        int starNum1 = new Random().nextInt(5);
        int starNum2 = new Random().nextInt(5);
        int starNum3 = new Random().nextInt(5);

        mSVMath.setStar(starNum1);
        mSVChinese.setStar(starNum2);
        mSVEnglish.setStar(starNum3);

        float radio1 = new Random().nextFloat();
        float radio2 = new Random().nextFloat();
        float radio3 = new Random().nextFloat();
        float radio4 = new Random().nextFloat();

        float totalRadio = (starNum1 + starNum2 + starNum3) / 15f;//星级 占比

        float totalNowRadio = (radio1 + radio2 + radio3 + radio4) / 4;
        float r1 = radio1 / totalNowRadio * totalRadio;
        float r2 = radio2 / totalNowRadio * totalRadio;
        float r3 = radio3 / totalNowRadio * totalRadio;
        float r4 = radio4 / totalNowRadio * totalRadio;

        progressView1.initData(r1 > 1 ? 1 : r1);
        progressView2.initData(r2 > 1 ? 1 : r2);
        progressView3.initData(r3 > 1 ? 1 : r3);
        progressView4.initData(r4 > 1 ? 1 : r4);

    }
}
