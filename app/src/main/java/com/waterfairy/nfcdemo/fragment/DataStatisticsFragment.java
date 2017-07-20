package com.waterfairy.nfcdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.waterfairy.nfcdemo.dialog.ListDialog;
import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.widget.ProgressView;
import com.waterfairy.tools.ResSizeUtils;

import java.util.ArrayList;
import java.util.List;

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
        progressView1.initData(0.9f);
        progressView2.initData(0.4f);
        progressView3.initData(0.6f);
        progressView4.initData(0.2f);

    }

    @OnClick(R.id.info)
    public void onInfoClick(View view) {

    }

    @OnClick({R.id.student_grade, R.id.student_class})
    public void onGradeClick(View view) {
        if (view.getId() == R.id.student_grade) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                list.add((i + 1) + "年级");
            }
            int[] gradeCoordinate = getGradeCoordinate(mTVGrade);
            ListDialog listDialog = new ListDialog(getActivity(), mTVGrade, ListDialog.TAG_GRADE, gradeCoordinate[0], gradeCoordinate[1], gradeCoordinate[2], list, this);
            listDialog.show();
        } else {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                list.add((i + 1) + "班");
            }
            int[] gradeCoordinate = getGradeCoordinate(mTVClass);
            ListDialog listDialog = new ListDialog(getActivity(), mTVClass, ListDialog.TAG_CLASS, gradeCoordinate[0], gradeCoordinate[1], gradeCoordinate[2], list, this);
            listDialog.show();
        }
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

    }

    @Override
    public void onSelect(View view, int tag, int pos) {

    }
}
