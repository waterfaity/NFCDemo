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
import com.waterfairy.nfcdemo.bean.ClassInfoBean;
import com.waterfairy.nfcdemo.bean.DisciplineCodeBean;
import com.waterfairy.nfcdemo.bean.StudentBean;
import com.waterfairy.nfcdemo.database.EvaluationDB;
import com.waterfairy.nfcdemo.dialog.EvaluateDialog;
import com.waterfairy.nfcdemo.dialog.ListDialog;
import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.adapter.StudentAdapter;
import com.waterfairy.nfcdemo.dialog.NoteDialog;
import com.waterfairy.nfcdemo.presenter.RequestPresenter;
import com.waterfairy.nfcdemo.utils.DataTransUtils;
import com.waterfairy.tools.ResSizeUtils;
import com.waterfairy.tools.ToastUtils;
import com.xueduoduo.application.MyApp;

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

public class ClassroomAssessmentFragment extends android.support.v4.app.Fragment implements ListDialog.OnSpinnerSelectListener, AdapterView.OnItemClickListener, EvaluateDialog.onEvaluateListener {
    private View mRootView;
    @BindView(R.id.grid_view)
    GridView mGV;
    @BindView(R.id.student_class)
    TextView mTVClass;
    @BindView(R.id.student_grade)
    TextView mTVGrade;
    private RequestPresenter requestPresenter = new RequestPresenter();

    private List<DisciplineCodeBean> disciplineCodeBeenList;

    private StudentAdapter studentAdapter;
    private StudentBean clickStudent;
    private ClassInfoBean classInfoBean;


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


    }

    private void initData() {
        new RequestPresenter().requestClassInfo(new RequestPresenter.OnRequestListener() {
            @Override
            public void onSuccess(Object object) {
                ArrayList<ClassInfoBean> classInfoBeen = (ArrayList<ClassInfoBean>) object;
                if (classInfoBeen != null && classInfoBeen.size() > 0) {
                    mTVGrade.setText(classInfoBeen.get(0).getClassName());
                    classInfoBean = classInfoBeen.get(0);
                    requestStudent(classInfoBean.getClassId());
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

    private void requestStudent(long classId) {
        requestPresenter.requestClassStudent(classId, new RequestPresenter.OnRequestListener() {

            @Override
            public void onSuccess(Object object) {
                List<StudentBean> list = (List<StudentBean>) object;
                studentAdapter = new StudentAdapter(getActivity(), list);
                mGV.setAdapter(studentAdapter);
                studentAdapter.setOnItemClickListener(ClassroomAssessmentFragment.this);
            }

            @Override
            public void onError(String message) {
                mGV.setAdapter(studentAdapter = new StudentAdapter(getActivity(), null));
            }
        });

        requestPresenter.requestTeacherDiscipline(classId, new RequestPresenter.OnRequestListener() {

            @Override
            public void onSuccess(Object object) {
                disciplineCodeBeenList = (List<DisciplineCodeBean>) object;
            }

            @Override
            public void onError(String message) {
                disciplineCodeBeenList = null;
            }
        });
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
        ListDialog listDialog = new ListDialog(getActivity(), mTVGrade, ListDialog.TAG_GRADE, gradeCoordinate[0], gradeCoordinate[1], gradeCoordinate[2], listStr, ClassroomAssessmentFragment.this);
        listDialog.show();
    }

    @Override
    public void onSelect(View textView, int tag, int pos) {
        if (tag == TAG_EVA) {
            if (pos == 0) {
                if (clickStudent != null && !EvaluateDialog.isShow) {

                    new EvaluateDialog(getActivity(), clickStudent, disciplineCodeBeenList).setOnEvaluateListener(this).show();
                    EvaluateDialog.isShow = true;
                }
            } else {
                if (clickStudent != null) {
                    Intent intent = new Intent(getActivity(), EvaluationDetailActivity.class);
                    clickStudent.setClassName(classInfoBean.getClassName());
                    intent.putExtra("studentBean", clickStudent);
                    startActivity(intent);
                }
            }
        } else if (tag == TAG_GRADE) {
            ArrayList<ClassInfoBean> classInfo = MyApp.getInstance().getClassInfo();
            if (classInfo != null && classInfo.size() > 0) {
                classInfoBean = classInfo.get(pos);
                requestStudent(classInfoBean.getClassId());
            }
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
        clickStudent = (StudentBean) studentAdapter.getItem(position);
        clickStudent.setClassName(classInfoBean.getClassName());
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
        ListDialog listDialog = new ListDialog(getActivity(), view, TAG_EVA, left, top, gradeCoordinate[2] * 5 / 6, list, this);
        listDialog.show();
        view.getTop();
    }

    @Override
    public void onEvaluate(EvaluationDB evaluationDB) {
//        new NoteDialog(getActivity(), "评价成功").show();
        ToastUtils.show("评价成功");
    }
}
