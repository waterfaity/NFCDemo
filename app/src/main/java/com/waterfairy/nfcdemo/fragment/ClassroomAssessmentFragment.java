package com.waterfairy.nfcdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.waterfairy.nfcdemo.activity.EvaluationDetailActivity;
import com.waterfairy.nfcdemo.bean.StudentBean;
import com.waterfairy.nfcdemo.dialog.EvaluateDialog;
import com.waterfairy.nfcdemo.dialog.ListDialog;
import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.adapter.StudentAdapter;
import com.waterfairy.tools.ResSizeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.waterfairy.nfcdemo.dialog.ListDialog.TAG_CLASS;
import static com.waterfairy.nfcdemo.dialog.ListDialog.TAG_EVA;
import static com.waterfairy.nfcdemo.dialog.ListDialog.TAG_GRADE;

/**
 * Created by water_fairy on 2017/7/19.
 * 995637517@qq.com
 */

public class ClassroomAssessmentFragment extends android.support.v4.app.Fragment implements ListDialog.OnSpinnerSelectListener, AdapterView.OnItemClickListener {
    private View mRootView;
    @BindView(R.id.grid_view)
    GridView mGV;
    @BindView(R.id.student_class)
    TextView mTVClass;
    @BindView(R.id.student_grade)
    TextView mTVGrade;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_classroom_assessemnt, container, false);
        ButterKnife.bind(this, mRootView);
        initData();
        initView();
        return mRootView;
    }

    private void initView() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("fda" + i);
        }
        StudentAdapter studentAdapter = new StudentAdapter(getActivity(), list);
        mGV.setAdapter(studentAdapter);
        studentAdapter.setOnItemClickListener(this);
    }

    private void initData() {

    }

    @OnClick({R.id.student_grade, R.id.student_class})
    public void onGradClick(View view) {
        if (view.getId() == R.id.student_grade) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                list.add((i + 1) + "年级");
            }
            int[] gradeCoordinate = getGradeCoordinate(mTVGrade);
            ListDialog listDialog = new ListDialog(getActivity(), mTVGrade, TAG_GRADE, gradeCoordinate[0], gradeCoordinate[1], gradeCoordinate[2], list, this);
            listDialog.show();
        } else {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                list.add((i + 1) + "班");
            }
            int[] gradeCoordinate = getGradeCoordinate(mTVClass);
            ListDialog listDialog = new ListDialog(getActivity(), mTVClass, TAG_CLASS, gradeCoordinate[0], gradeCoordinate[1], gradeCoordinate[2], list, this);
            listDialog.show();
        }
    }

    @Override
    public void onSelect(View textView, int tag, int pos) {
        if (tag == TAG_EVA) {
            if (pos == 0) {
                new EvaluateDialog(getActivity(), new StudentBean()).show();
            } else {
                startActivity(new Intent(getActivity(), EvaluationDetailActivity.class));
            }
        } else if (tag == TAG_GRADE) {

        } else if (tag == TAG_CLASS) {

        }
    }

    private int[] getGradeCoordinate(View view) {
        View parent1 = (View) mTVGrade.getParent();
        View parent2 = (View) parent1.getParent();

        return new int[]{
                view.getLeft() + parent1.getLeft() + parent2.getLeft(),
                parent1.getTop() + parent2.getTop() + view.getBottom() +
                        ResSizeUtils.getStatusBarHeight(getActivity())
                        + ResSizeUtils.getActionBarHeight(getActivity()), view.getRight() - view.getLeft()};
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        List<String> list = new ArrayList<>();
        list.add("去评价");
        list.add("评价详情");
        int[] gradeCoordinate = getGradeCoordinate(view);
        int left = gradeCoordinate[0];
        int top = gradeCoordinate[1];
        int height = view.getBottom() - view.getTop();
        if (top + height > getResources().getDisplayMetrics().heightPixels) {
            top = getResources().getDisplayMetrics().heightPixels - height;
        }
        top += (height) / 4;
        int halfWidth = (view.getRight() - view.getLeft()) * 2 / 3;
        if (position % 3 != 2) {
            left += halfWidth;
        } else {
            left -= halfWidth;
            left += gradeCoordinate[2] / 4;
        }
        ListDialog listDialog = new ListDialog(getActivity(), view, TAG_EVA, left, top, gradeCoordinate[2] * 3 / 4, list, this);
        listDialog.show();
        view.getTop();
    }
}
