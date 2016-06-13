package com.lpd.android.myview.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.lpd.android.myview.R;

/**
 * Created by Administrator on 2016/6/2.
 */
public class CircleImageView extends ImageView {

    private final int DEFAULT_COLOR = Color.RED;
    private final float DEFAULT_WIDTH = 2f;

//
//    private Context mContext;
//
    private int mBorderColor = DEFAULT_COLOR;
    private float mBorderWidth = DEFAULT_WIDTH;
//
//
    private Paint mPaint;
//    private float mCircleRadius;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
//
//        //边框的颜色
//        mBorderColor = mTypedArray.getColor(R.styleable.CircleImageView_BorderColor, DEFAULT_COLOR);
//        //边框的宽度
//        mBorderWidth = mTypedArray.getDimension(R.styleable.CircleImageView_BorderWidth, DEFAULT_WIDTH);
//
//        mTypedArray.recycle();

        /**
         * 初始化Paint画笔
         */
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setColor(mBorderColor);
    }

    /**
     * Draw CircleImageView
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        Path path = new Path();

        canvas.save();
//        path.addCircle(100,100,100 - 5, Path.Direction.CW);



        canvas.drawColor(Color.BLUE);

//        canvas.clipPath(path);
//        canvas.drawColor(Color.RED);

//        mPaint.setColor(Color.GRAY);
//        canvas.drawCircle(100,100,100 - 10, mPaint);

        Bitmap bitmap = ((BitmapDrawable)getDrawable()).getBitmap();


        canvas.drawBitmap(bitmap, 0, 0, mPaint);
//        setLayerType(View.LAYER_TYPE_SOFTWARE,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR));
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(bitmap.getWidth()/2,bitmap.getWidth()/2,bitmap.getWidth()/2 - 10, mPaint);

        mPaint.setXfermode(null);

//        canvas.drawBitmap(bitmap, 0, 0, mPaint);
//        Path path = new Path();
//        path.addCircle(50, 50, 45, Path.Direction.CCW);
//
//        canvas.clipPath(path);
//
//        mPaint.setStrokeWidth(5f);
//        mPaint.setStyle(Paint.Style.STROKE);
//        RectF rectf = new RectF(0,0,100,100);
//        canvas.drawArc(rectf,0f,180f,true,mPaint);

        canvas.restore();


    }

}
