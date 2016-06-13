package com.lpd.android.fuheview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lpd.android.R;

/**
 * Created by Administrator on 2016/6/1.
 */
public class CustomSubBasicView extends LinearLayout implements View.OnClickListener{

    private  Button mBtnTongGuo,mBtnYinHuan,mBtnWeiShe;
    private ImageView mTypeImg;
    private TextView mTxtTitle;
    private View mChildContent;
    private View mView;

    private boolean isShowing = false;

    public CustomSubBasicView(Context context) {
        super(context);
    }

    public CustomSubBasicView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //加载View
        LayoutInflater.from(context).inflate(R.layout.custom_subbasic, this,true);

        //类型显示的图片提示
        mTypeImg = (ImageView)findViewById(R.id.id_basic_type_img);

        //title 点击收起Content
        mTxtTitle = (TextView)findViewById(R.id.id_basic_type_subTitle);

        //显示、隐藏的Content
        mChildContent = findViewById(R.id.id_basic_type_content);
        //默认隐藏
//        mChildContent.setVisibility(View.GONE);


        //同意Button
//        mBtnTongGuo = (Button) mView.findViewById(R.id.id_basic_btn_tongguo);
        //隐藏Button
//        mBtnYinHuan = (Button) mView.findViewById(R.id.id_basic_btn_yinhuan);
        //未涉Button
//        mBtnWeiShe = (Button) mView.findViewById(R.id.id_basic_btn_weishe);



//        mTxtTitle.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if( ! isShowing){
//                    mChildContent.setVisibility(View.VISIBLE);
//                    isShowing = true;
//                }else{
//                    mChildContent.setVisibility(View.VISIBLE);
//                    isShowing = false;
//                }
//
//            }
//        });




    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.id_basic_btn_tongguo://通过

                mTypeImg.setImageResource(R.drawable.nor_3x_pay_lv);
                mTxtTitle.setTextColor(Color.GREEN);


                break;
            case R.id.id_basic_btn_yinhuan://隐藏

                mTypeImg.setImageResource(R.drawable.nor_3x_pay);
                mTxtTitle.setTextColor(0xfc6531);

                break;
            case R.id.id_basic_btn_weishe://未设

                mTypeImg.setImageResource(R.drawable.nor_3x_pay_lan);
                mTxtTitle.setTextColor(Color.BLUE);

                break;

        }




    }
}
