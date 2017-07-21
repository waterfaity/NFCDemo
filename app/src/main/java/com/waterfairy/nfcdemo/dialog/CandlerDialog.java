package com.waterfairy.nfcdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import com.waterfairy.nfcdemo.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by water_fairy on 2017/7/21.
 * 995637517@qq.com
 */

public class CandlerDialog extends Dialog implements CalendarView.OnDateChangeListener, View.OnClickListener {
    private LinearLayout mRootView;
    private CalendarView calendarView;
    private OnDateSelectListener onDateSelectListener;


    public CandlerDialog(@NonNull Context context, long date, OnDateSelectListener onDateSelectListener) {
        super(context, R.style.dialog3);
        this.onDateSelectListener = onDateSelectListener;
        mRootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.dialog_calendar, null);
        setContentView(mRootView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        calendarView = (CalendarView) mRootView.findViewById(R.id.calendar_view);
        calendarView.setDate(date);
        calendarView.setOnDateChangeListener(this);
        mRootView.findViewById(R.id.ensure).setOnClickListener(this);
        mRootView.findViewById(R.id.close).setOnClickListener(this);
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ensure) {
            onDateSelectListener.onDateSelect(calendarView.getDate());
        }
        dismiss();
    }

    public interface OnDateSelectListener {
        void onDateSelect(long date);
    }
}
