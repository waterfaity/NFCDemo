package com.waterfairy.nfcdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.widget.FiveView;

public class TestViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);
        FiveView fiveView = (FiveView) findViewById(R.id.five_view);
        fiveView.initData(5, 0, false);
    }
}
