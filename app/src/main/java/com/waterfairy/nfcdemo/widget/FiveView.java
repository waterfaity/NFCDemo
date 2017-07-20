package com.waterfairy.nfcdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.icu.math.BigDecimal;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Printer;

import com.waterfairy.baseView.BaseSelfViewGroup;

/**
 * Created by water_fairy on 2017/7/19.
 * 995637517@qq.com
 */

public class FiveView extends BaseSelfViewGroup {
    private static final String TAG = "FiveView";
    private int solidColor = Color.parseColor("#fdae2d");
    private int lineColor = Color.parseColor("#777777");
    private int cont = 5;
    private int startAngle = 180;
    private float perAngle;
    private Path path1, path2;
    private boolean solid = true;
    private float mCenterX, mCenterY;
    private float mCenterLineLen;
    private Paint paint;


    public FiveView(Context context) {
        super(context);
    }

    public FiveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canDraw)
            drawStatic(canvas);
    }

    public void initData(int count, int startAngle, boolean solid) {
        startAngle += 180;
        if (count == 0) count = 5;
        this.cont = count;
        this.startAngle = startAngle;
        this.solid = solid;
        initpaint();
        onInitDataOk();

    }

    private void initpaint() {
        if (paint == null) {
            paint = new Paint();
            paint.setAntiAlias(true);
        }
        if (solid) {
            paint.setColor(solidColor);
        } else {
            paint.setColor(lineColor);
            paint.setStyle(Paint.Style.STROKE);
        }
    }

    @Override
    protected void beforeDraw() {
        initLen();
        initAngle();
    }

    private void initLen() {
        perAngle = 360f / cont;
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        mCenterLineLen = Math.min(mCenterX, mCenterY);
    }

    private void initAngle() {
        path1 = new Path();

        boolean isTwo = false;
        if (perAngle % 2 == 0) {
            path2 = new Path();
            isTwo = true;
        }
        float firX = 0, firY = 0;
        float firX2 = 0, firY2 = 0;
        for (int i = 0; i < cont; i++) {

            float angle = startAngle + i * perAngle;
            double v = Math.toRadians(angle);
            float x = (float) (mCenterX + Math.sin(v) * mCenterLineLen);
            float y = (float) (mCenterY + Math.cos(v) * mCenterLineLen);
            if (i == 0) {
                firX = x;
                firY = y;
                path1.moveTo(x, y);
            } else if (i == 1) {

            } else {
                if (i % 2 == 0) {
                    path1.lineTo(x, y);
                } else {
                    path2.lineTo(x, y);
                }
            }
            path1.lineTo(x, y);
            if (i + 1 == cont) {
                path1.lineTo(firX, firY);
            }

        }
    }


    private void drawStatic(Canvas canvas) {
        canvas.drawPath(path1, paint);
    }
}
