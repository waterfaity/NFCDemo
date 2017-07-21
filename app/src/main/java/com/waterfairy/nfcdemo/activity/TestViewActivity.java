package com.waterfairy.nfcdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.dialog.CandlerDialog;
import com.waterfairy.nfcdemo.widget.FiveView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestViewActivity extends AppCompatActivity implements CandlerDialog.OnDateSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);
        FiveView fiveView = (FiveView) findViewById(R.id.five_view);
        fiveView.initData(5, 0, false);

        new CandlerDialog(this, new Date().getTime(), this).show();
    }

    @Override
    public void onDateSelect(long date) {
        Log.i("teset", "onDateSelect: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date(date)));
    }
}
