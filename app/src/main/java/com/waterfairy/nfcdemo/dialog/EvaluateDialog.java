package com.waterfairy.nfcdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.bean.StudentBean;
import com.waterfairy.nfcdemo.database.EvaluationDB;
import com.waterfairy.nfcdemo.widget.StartView;
import com.xueduoduo.application.MyApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by water_fairy on 2017/7/20.
 * 995637517@qq.com
 */

public class EvaluateDialog extends Dialog implements View.OnClickListener {
    StartView startView1;
    StartView startView2;
    StartView startView3;
    StartView startView4;
    private LinearLayout mRootView;
    private StudentBean studentBean;
    private onEvaluateListener listener;


    public EvaluateDialog(@NonNull Context context, StudentBean studentBean) {
        super(context, R.style.dialog3);
        this.studentBean = studentBean;
        mRootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.dialog_evaluate, null);
        addContentView(mRootView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initView();
    }

    private void initView() {
        startView1 = (StartView) mRootView.findViewById(R.id.star1);
        startView2 = (StartView) mRootView.findViewById(R.id.star2);
        startView3 = (StartView) mRootView.findViewById(R.id.star3);
        startView4 = (StartView) mRootView.findViewById(R.id.star4);
        OnDismissClickListener onDismissClickListener = new OnDismissClickListener();
        mRootView.findViewById(R.id.close).setOnClickListener(onDismissClickListener);
        mRootView.findViewById(R.id.save).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        List<EvaluationDB> list = new ArrayList<>();

        list.add(createDB(EvaluationDB.TYPE_LISTEN, startView1));
        list.add(createDB(EvaluationDB.TYPE_READ_ALOUD, startView2));
        list.add(createDB(EvaluationDB.TYPE_READ, startView3));
        list.add(createDB(EvaluationDB.TYPE_SPEAK, startView4));
        if (listener != null) listener.onEvaluate(list);
        dismiss();

    }

    private EvaluationDB createDB(int type, StartView startView) {
        EvaluationDB evaluationDB = new EvaluationDB();
        evaluationDB.setScore(startView.getStarNum());
        evaluationDB.setStudentId(studentBean.getId());
        evaluationDB.setType(type);
        evaluationDB.setTeacherId(MyApp.getUserBean().getUserId());
        evaluationDB.setSubject(MyApp.getUserBean().getSubject());
        evaluationDB.setTime(System.currentTimeMillis());
        MyApp.getInstance().getDaoSession().getEvaluationDBDao().insert(evaluationDB);
        return evaluationDB;
    }


    public class OnDismissClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            dismiss();
        }
    }

    public void setOnEvaluateListener(onEvaluateListener listener) {
        this.listener = listener;
    }

    public interface onEvaluateListener {
        void onEvaluate(List<EvaluationDB> list);
    }

}