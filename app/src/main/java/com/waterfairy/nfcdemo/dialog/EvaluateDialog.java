package com.waterfairy.nfcdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.bean.DisciplineCodeBean;
import com.waterfairy.nfcdemo.bean.StudentBean;
import com.waterfairy.nfcdemo.database.EvaluationDB;
import com.waterfairy.nfcdemo.utils.DataTransUtils;
import com.waterfairy.nfcdemo.widget.StartView;
import com.waterfairy.tools.ResSizeUtils;
import com.waterfairy.transformation.BitmapCircleTransformation;
import com.xueduoduo.application.MyApp;

import java.util.List;

/**
 * Created by water_fairy on 2017/7/20.
 * 995637517@qq.com
 */

public class EvaluateDialog extends Dialog implements View.OnClickListener, ListDialog.OnSpinnerSelectListener, DialogInterface.OnDismissListener {
    StartView startView1;
    StartView startView2;
    StartView startView3;
    StartView startView4;
    private TextView mSubject;
    private LinearLayout mRootView;
    private StudentBean studentBean;
    private onEvaluateListener listener;
    private List<DisciplineCodeBean> disciplineCodeBean;
    private Context context;
    private DisciplineCodeBean currentDiscipline;
    private TextView mClass, mName;
    private ImageView mHead;
    public static boolean isShow;


    public EvaluateDialog(@NonNull Context context, StudentBean studentBean, List<DisciplineCodeBean> disciplineCodeBeenList) {
        super(context, R.style.dialog3);
        this.studentBean = studentBean;
        this.context = context;
        this.disciplineCodeBean = disciplineCodeBeenList;
        mRootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.dialog_evaluate, null);
        addContentView(mRootView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initView();
        initData(context, studentBean, disciplineCodeBeenList);
    }

    public void initData(Context context, StudentBean studentBean, List<DisciplineCodeBean> disciplineCodeBean) {
        this.context = context;
        this.studentBean = studentBean;
        startView1.setStar(0);
        startView2.setStar(0);
        startView3.setStar(0);
        startView4.setStar(0);
        if (disciplineCodeBean != null && disciplineCodeBean.size() > 0) {
            mSubject.setText(disciplineCodeBean.get(0).getDisciplineName());
            currentDiscipline = disciplineCodeBean.get(0);
        }
        mName.setText(this.studentBean.getUserName());
        mClass.setText(this.studentBean.getClassName());
        try {
            Glide.with(this.context).load(this.studentBean.getLogoUrl()).transform(new BitmapCircleTransformation(this.context))
                    .error(R.mipmap.ic_user_head_default).into(mHead);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initView() {

        startView1 = (StartView) mRootView.findViewById(R.id.star1);
        startView2 = (StartView) mRootView.findViewById(R.id.star2);
        startView3 = (StartView) mRootView.findViewById(R.id.star3);
        startView4 = (StartView) mRootView.findViewById(R.id.star4);
        OnDismissClickListener onDismissClickListener = new OnDismissClickListener();
        mRootView.findViewById(R.id.close).setOnClickListener(onDismissClickListener);
        mRootView.findViewById(R.id.save).setOnClickListener(this);
        mSubject = ((TextView) mRootView.findViewById(R.id.subject));
        mSubject.setOnClickListener(this);
        mName = (TextView) mRootView.findViewById(R.id.student_name);
        mClass = (TextView) mRootView.findViewById(R.id.student_class);
        mHead = (ImageView) mRootView.findViewById(R.id.user_icon);
        setOnDismissListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save) {
            long userId = MyApp.getInstance().getUserInfo().getUserId();
            EvaluationDB evaluationDB = new EvaluationDB();
            evaluationDB.setTime(System.currentTimeMillis());
            evaluationDB.setTeacherId(userId);
            evaluationDB.setStudentId(studentBean.getUserId());
            evaluationDB.setSubject(currentDiscipline.getDisciplineCode());
            evaluationDB.setListenScore(startView1.getStarNum());
            evaluationDB.setReadAloudScore(startView2.getStarNum());
            evaluationDB.setReadScore(startView3.getStarNum());
            evaluationDB.setSpeakScore(startView4.getStarNum());
            MyApp.getInstance().getDaoSession().getEvaluationDBDao().insert(evaluationDB);
            if (listener != null) listener.onEvaluate(evaluationDB);
            dismiss();
        } else if (v.getId() == R.id.subject) {
            int[] gradeCoordinate = getGradeCoordinate(mSubject);
            List<String> stringList = DataTransUtils.transSubjectToString(disciplineCodeBean);
            new ListDialog(context, mSubject, ListDialog.TAG_SUBJECT, gradeCoordinate[0], gradeCoordinate[1], gradeCoordinate[2] * 2, stringList, this).show();
        }
    }

    private int[] getGradeCoordinate(View view) {
        View parent1 = (View) mSubject.getParent();
        View parent2 = (View) parent1.getParent();

        return new int[]{
                view.getLeft() + parent1.getLeft() + parent2.getLeft(),
                parent1.getTop() + parent2.getTop() + view.getBottom() +
                        ResSizeUtils.getStatusBarHeight(context)
                        + ResSizeUtils.getActionBarHeight(context), view.getRight() - view.getLeft()};
    }


    @Override
    public void onSelect(View view, int tag, int pos) {
        currentDiscipline = this.disciplineCodeBean.get(pos);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        isShow = false;
    }


    public class OnDismissClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            dismiss();
        }
    }

    public EvaluateDialog setOnEvaluateListener(onEvaluateListener listener) {
        this.listener = listener;
        return this;
    }

    public interface onEvaluateListener {
        void onEvaluate(EvaluationDB evaluationDB);
    }
}