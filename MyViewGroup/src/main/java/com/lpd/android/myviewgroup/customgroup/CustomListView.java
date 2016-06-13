package com.lpd.android.myviewgroup.customgroup;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lpd.android.myviewgroup.R;

import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/6/3.
 */
public class CustomListView extends ListView implements AbsListView.OnScrollListener {


    /*View*
     * 记录当前显示的第一条item项如果为0则在ListView的头部
     */
    private int mFirstVisibleItem;
    private boolean isTop;
    private View  headInflateView;
    private int paddingRight;
    private int paddingLeft;
    private int paddingBottom;
    private boolean hasMove;
    private Context mContext;

    private ImageView mAnimImg;

    private long mAnimDuration;

    private Animator mAnimtor;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private int headHeight;

    public CustomListView(Context context) {
        super(context);

        initView(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    /**
     * 初始化View
     */
    public void initView(Context context) {

        mContext = context;

        headInflateView = LayoutInflater.from(context).inflate(R.layout.header_listview_layout, null);

        mAnimImg = (ImageView)headInflateView.findViewById(R.id.anim_img);
        mAnimImg.setBackgroundResource(R.drawable.anim_list_head);

        AnimationDrawable animation = (AnimationDrawable) mAnimImg.getBackground();
        animation.start();

        /**
         * 添加到ListView头部View
         */
        this.addHeaderView(headInflateView);

         headHeight = headInflateView.getMeasuredHeight();
        int headWidth = headInflateView.getMeasuredWidth();

         paddingLeft = headInflateView.getPaddingLeft();
         paddingRight = headInflateView.getPaddingRight();
         paddingBottom = headInflateView.getPaddingBottom();


        //添加Touch监听
        this.setOnScrollListener(this);


    }

    private int startY ;
    private int endY ;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch(ev.getAction()){

            case MotionEvent.ACTION_DOWN://手指落下时
                if(mFirstVisibleItem == 0){
                    isTop = true;
                    startY = (int) ev.getRawY();
                }

                break;
            case MotionEvent.ACTION_MOVE://手指移动时

                onMyMove(ev);
                hasMove = true;


                break;
            case MotionEvent.ACTION_UP://手抬起时

                if(hasMove){

                    /**
                     * 恢复位置
                     */
                    headInflateView.setPadding(paddingLeft, -headHeight, paddingRight, paddingBottom);
                    headInflateView.postInvalidate();

                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            headInflateView.setVisibility(View.GONE);
                        }
                    }, 3000);

                    hasMove = false;

                }

            default://默认
                break;

        }


        return true;
    }

    private void onMyMove(MotionEvent ev) {

        if(isTop){

            endY = (int) ev.getRawY();

            int temp = endY - startY;

            headInflateView.setVisibility(View.VISIBLE);
            headInflateView.setPadding(paddingLeft, temp, paddingRight, paddingBottom);
            headInflateView.postInvalidate();

        }



    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    /**
     * 判断当前是否滑动到ListView的顶部
     *
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        mFirstVisibleItem = firstVisibleItem;

    }
}
