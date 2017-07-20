package com.waterfairy.nfcdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.adapter.EvaDetailAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EvaluationDetailActivity extends AppCompatActivity {
    @BindView(R.id.list_view)
    ListView mLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mLV.setAdapter(new EvaDetailAdapter(this));
    }

    @OnClick(R.id.back)
    public void back(View view) {
        finish();
    }
}
