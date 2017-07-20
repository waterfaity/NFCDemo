package com.waterfairy.nfcdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.waterfairy.baseView.BaseSelfViewGroup;
import com.waterfairy.nfcdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by water_fairy on 2017/7/19.
 * 995637517@qq.com
 */

public class StartView extends LinearLayout implements View.OnTouchListener {
    private Context context;
    private float width;
    private float height;
    private float space;//间隔
    private int solidStarRes = R.mipmap.icon_star;
    private int emptyStarRes = R.mipmap.icon_star_empty;
    private List<ImageView> stars;
    private int starNum = 1;
    private boolean isFirst = true;
    private boolean canChange;

    public StartView(Context context) {
        super(context);
    }

    public StartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StartView);
        starNum = a.getInt(R.styleable.StartView_starNum, 1);
        canChange = a.getBoolean(R.styleable.StartView_canChange, false);
        this.context = context;
        setOnTouchListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        if (width != 0 && isFirst) {
            isFirst = false;
            initView();
        }

    }

    private void initView() {
        space = (width - 5 * height) / 4;
        stars = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(context);
            addView(imageView);
            LinearLayout.LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
            layoutParams.height = (int) height;
            layoutParams.width = (int) height;
            if (i != 0) {
                layoutParams.leftMargin = (int) space;
            }
            imageView.setLayoutParams(layoutParams);
            imageView.setBackground(context.getResources().getDrawable(emptyStarRes));
            stars.add(imageView);
        }
        setStar(starNum);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (!canChange) return false;
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_MOVE) {
            for (int i = 0; i < 5; i++) {
                int x1 = (int) (i * height) + i > 1 ? i - 1 : 0;
                int x2 = (int) ((i + 1) * height + space * i);
                float x = event.getX();
                if (x > x1 && x < x2) {
                    if (i + 1 == starNum) {
                        return true;
                    }
                    setStar(i + 1);
                    return true;
                }
            }
        }
        return true;
    }

    public void setStar(int star) {
        Log.i("starView", "setStar: " + star);
        starNum = star;
        for (int i = 0; stars != null && i < stars.size(); i++) {
            ImageView imageView = stars.get(i);
            if (star < i + 1) {
                imageView.setBackground(getResources().getDrawable(emptyStarRes));
            } else {
                imageView.setBackground(getResources().getDrawable(solidStarRes));
            }
        }
    }

    public int getStarNum() {
        return starNum;
    }
}
