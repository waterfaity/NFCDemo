package com.waterfairy.nfcdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.waterfairy.baseView.BaseSelfViewGroup;
import com.waterfairy.baseView.BaseSurfaceView;

/**
 * Created by water_fairy on 2017/7/19.
 * 995637517@qq.com
 */

public class ProgressView extends BaseSelfViewGroup {

    private int healthColor = Color.parseColor("#0fca81");
    private int unHealthColor = Color.parseColor("#ffdf3d");
    private int bgColor = Color.parseColor("#f1f1f1");
    private Paint paint, bgPaint;
    private int color;
    private float radius;
    private float height;
    private float rate;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void beforeDraw() {
        radius = mWidth / 2;
        height = (mHeight - 2 * radius) * rate;
    }

    public void initData(float rate) {
        this.rate = rate;
        if (rate >= 0.5) color = healthColor;
        else color = unHealthColor;
        initPaint();
        onInitDataOk();

    }

    private void initPaint() {
        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);
        }
        paint.setColor(color);
        if (bgPaint == null) {
            bgPaint = new Paint();
            bgPaint.setAntiAlias(true);
            bgPaint.setColor(bgColor);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canDraw) {
            drawStatic(canvas);
        }
    }

    private void drawStatic(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        //背景
        canvas.drawCircle(radius, radius, radius, bgPaint);
        canvas.drawCircle(radius, mHeight - radius, radius, bgPaint);
        canvas.drawRect(0, radius, mWidth, mHeight - radius, bgPaint);

        canvas.drawCircle(radius, mHeight - radius - height, radius, paint);
        canvas.drawCircle(radius, mHeight - radius, radius, paint);
        canvas.drawRect(0, mHeight - radius - height, mWidth, mHeight - radius, paint);
    }

}
