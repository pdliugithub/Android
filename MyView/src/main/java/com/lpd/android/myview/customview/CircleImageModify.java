package com.lpd.android.myview.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/6/2.
 *
 * 实现圆形图片
 */
public class CircleImageModify extends ImageView {
    public CircleImageModify(Context context) {
        super(context);
    }

    public CircleImageModify(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageModify(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2f);
        paint.setColor(Color.BLUE);


        /**
         * 画圆形图片\使用xfermode模式
         */
        Bitmap bitmap = ((BitmapDrawable)getDrawable()).getBitmap();

//        Drawable drawable = getResources().getDrawable(R.drawable.jqr);
//        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int min = width < height ? width : height;


        /**
         * 使用clip剪切圆形图片
         */
        Bitmap src1 = clipCircleImage(bitmap, min);

        /**
         * 使用xfermode的模式实现圆形图片
         */
        Bitmap src2 = createCircleImage(bitmap, min);

        /**
         * 使用BitmapShader实现圆形图片
         */
        Bitmap src3 = shaderCircleImage(bitmap, min);

//        setImageBitmap(src1);
//        setImageBitmap(src2);
        setImageBitmap(src3);

    }

    private Bitmap shaderCircleImage(Bitmap bitmap, int min) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Bitmap dest = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));//使用BitmapShader绘制图片

        Canvas canvas = new Canvas(dest);

        canvas.drawCircle(bitmap.getWidth()/2, bitmap.getWidth()/2, bitmap.getWidth()/2, paint);

        return dest;

    }

    /**
     * 通过剪切，实现原型图片效果
     * @param bitmap
     * @param min
     * @return
     */
    private Bitmap clipCircleImage(Bitmap bitmap, int min) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2f);

        Bitmap dest = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(dest);

        /**
         * 将canvas画布切割成圆形
         */
        Path path = new Path();
        path.addCircle(dest.getWidth()/2, dest.getWidth()/2, dest.getWidth()/2, Path.Direction.CCW);
        path.close();

        canvas.clipPath(path);

        /**
         * 将图片绘制到圆形的画布上，从而实现圆形图片的效果
         */
        canvas.drawBitmap(bitmap,0f, 0f, paint);


        return dest;
    }


    /**
     * 根据原图和变长绘制圆形图片
     *
     * @param source
     * @param min
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int min)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        /**
         * 当需要绘制图片的时候Paint对象必须还原初始值，否则效果无法实现，此处耗费多时。。。。。
         */
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(2f);
//        paint.setStyle(Paint.Style.STROKE);
        /**
         * 创建一个和原图一样大小的Bitmap，但是drawBitmap时必须指定原图source
         */
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形,[如果先绘制图片，再绘制圆形，则无论设置xfermode的模式是什么类型，都无法实现圆形图片效果，
         *  目前由于能力有限，尚无法解惑。。。。。。。。]
         */
        canvas.drawCircle(min / 2 , min / 2 , min / 2 - 2, paint);
//        canvas.drawBitmap(source, 0, 0, paint);
        /**
         * 使用SRC_IN，目前实现圆形图片，使用【SRC_IN】类型【网上最多使用的是DST_IN...可是我却无法实现。。。失效。。。？】
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
//        canvas.drawCircle(min / 2, min / 2, min / 2 - 5, paint);

        /**
         * 添加边框绘制
         */
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3f);
        canvas.drawCircle(min / 2 , min / 2 , min / 2 -5, paint);

        return target;
    }

}
