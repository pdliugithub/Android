package com.lpd.myapplication.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2016/6/11.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private Context mContext;

    private SurfaceHolder mSurfaceHolder;

    private Canvas mCanvas;

    private Paint mCirclePaint;

    private float mRadius;

    private boolean isDrawing;

    private Thread thread;

    private Paint mPaint;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        isDrawing = true;

        /*
        开启次线程进行绘制
         */
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        isDrawing = false;

    }

    @Override
    public void run() {

        mCanvas = mSurfaceHolder.lockCanvas();

        int radius = getWidth() / 2;

        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);


        for (int i = radius / 10; i > 0 ; i--) {

            mCanvas.drawCircle(radius, radius, radius - (i * 15), mPaint);

        }

        mSurfaceHolder.unlockCanvasAndPost(mCanvas);

    }

}
