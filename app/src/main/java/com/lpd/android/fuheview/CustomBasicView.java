package com.lpd.android.fuheview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lpd.android.R;

/**
 * Created by Administrator on 2016/6/1.
 */
public class CustomBasicView extends LinearLayout{

    private  TextView mTxtTitle;
    private  TextView mTxtDownUp;
    private  View mChildView;

    /**
     * 设置是否显示childView
     */
    private boolean isShowing = false;
    /**
    *加载的布局View
     */
    private View mView;

    public CustomBasicView(Context context) {
        this(context, null);
    }

    public CustomBasicView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.custom_layout, this,true);


        //Title
//         mTxtTitle = (TextView) findViewById(R.id.id_custom_tv_title);

        //显示、隐藏内容
//        mTxtDownUp = (TextView) findViewById(R.id.id_basic_downUp);
//
//        mChildView = findViewById(R.id.id_basic_include_content);


//        mTxtDownUp.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if( !isShowing){
//                    setVisibility(View.VISIBLE);
//                    mTxtDownUp.setBackgroundResource(R.drawable.down3x_nav_xia);
//                    isShowing = true;
//                }else{
//                    setVisibility(View.GONE);
//                    mTxtDownUp.setBackgroundResource(R.drawable.down3x_nav_shou);
//                }
//
//            }
//        });

    }

    public void setVisibilily(int visibilily){
        mChildView.setVisibility(visibilily);
    }

    /**
     * 设置显示的Title
     * @param title
     */
    public void setTitleText(String title){

        if(title == null){
            mTxtTitle.setText("null");
        }else{
            mTxtTitle.setText(title);
        }

    }

}
