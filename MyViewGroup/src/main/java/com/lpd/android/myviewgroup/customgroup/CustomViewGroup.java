package com.lpd.android.myviewgroup.customgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/6/4.
 */
public class CustomViewGroup extends ViewGroup {

    public CustomViewGroup(Context context) {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 计算所有ChildView的宽度和高度 然后根据ChildView的计算结果，设置自己的宽和高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**
         *获取此ViewGroup上级容器为其推荐的宽、高，以及计算模式
         */
        /**首先
         * 获取宽、高模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        /**首先
         * 获取宽、高的Size
         */
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //计算出所有ChildView的宽、高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //得到ChildView的Count
        int childCount = getChildCount();

        int lineWidth = 0;
        int lineHeight = 0;
        int width = 0;
        int height = 0;
        /**
         * for循环
         */
        for (int i = 0; i < childCount; i++) {

            /**
             * 通过At索引得到ChildView
             */
            View childView = getChildAt(i);

            //测量ChildView
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();

            int childWidth = childView.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = childView.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            if(lineWidth + childWidth > widthSize){

                width = Math.max(lineWidth , childWidth);

                lineWidth = childWidth;

                height += lineHeight;

                lineHeight = childHeight;
            }else{
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);

            }
            if(i == childCount -1){
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }

        }

        /**
         * 1、如果设置match_parent|固定值   测量模式为：MeasureSpec.EXACTLY
         * 2、如果设置wrap_content   测量模式为：MeasureSpec.AT_MOST
         * 3、                       测量模式为：MeasureSpec.UNSPECIFIED
          */
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize : width , (heightMode == MeasureSpec.EXACTLY) ? heightSize : height);

    }
    /**
     * onLayout对所有的子类进行布局位置的绘制
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
        }

    }
    /**
     * 调用dispatchDraw ()方法绘制子视图(如果该View类型不为ViewGroup，即不包含子视图，不需要重载该方法)
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
    /**
     * 调用onDraw()方法绘制视图本身   (每个View都需要重载该方法，ViewGroup不需要实现该方法)
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }
}
