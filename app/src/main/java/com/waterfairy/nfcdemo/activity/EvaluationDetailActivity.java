package com.waterfairy.nfcdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.adapter.EvaDetailAdapter;
import com.waterfairy.nfcdemo.bean.StudentBean;
import com.waterfairy.nfcdemo.database.EvaluationDB;
import com.waterfairy.nfcdemo.dialog.CandlerDialog;
import com.waterfairy.nfcdemo.presenter.DetailPresenter;
import com.waterfairy.nfcdemo.view.DetailView;
import com.waterfairy.transformation.BitmapCircleTransformation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EvaluationDetailActivity extends AppCompatActivity implements DetailView, CandlerDialog.OnDateSelectListener, AbsListView.OnScrollListener {
    private StudentBean studentBean;
    private DetailPresenter mPresenter;
    @BindView(R.id.list_view)
    ListView mLV;
    @BindView(R.id.student_class)
    TextView mClass;
    @BindView(R.id.student_name)
    TextView mName;
    @BindView(R.id.user_icon)
    ImageView mUserIcon;
    @BindView(R.id.date)
    TextView mDate;
    private List<EvaluationDB> evaluationDBs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_detail);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        mPresenter = new DetailPresenter(this);
        studentBean = (StudentBean) getIntent().getSerializableExtra("studentBean");
        mPresenter.getEvaluateData(studentBean.getUserId(), new Date().getTime());
    }

    private void initView() {

        mName.setText(studentBean.getUserName());
        mClass.setText(studentBean.getClassName());
        Glide.with(this).load(studentBean.getLogoUrl()).
                transform(new BitmapCircleTransformation(this)).error(R.mipmap.ic_user_head_default).into(mUserIcon);
        mLV.setOnScrollListener(this);
    }

    @OnClick(R.id.back)
    public void back(View view) {
        finish();
    }

    @OnClick(R.id.date_card_view)
    public void onDateSelectClick(View view) {
        new CandlerDialog(this, new Date().getTime(), this).show();
    }

    @Override
    public void onDateSelect(long date) {
        mDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(date)));
        mPresenter.getEvaluateData(studentBean.getUserId(), date);
    }

    @Override
    public void displayData(List<EvaluationDB> evaluationDBs) {
        this.evaluationDBs = evaluationDBs;
        mLV.setAdapter(new EvaDetailAdapter(this, evaluationDBs));
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (totalItemCount != 0) {
            EvaluationDB evaluationDB = evaluationDBs.get(firstVisibleItem);
            mDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(evaluationDB.getTime())));
        }

    }
}
