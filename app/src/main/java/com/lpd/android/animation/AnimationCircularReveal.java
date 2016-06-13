package com.lpd.android.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.lpd.android.R;

public class AnimationCircularReveal extends AppCompatActivity {


    private boolean  flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_circular_reveal);

        Button mBtnStart = (Button) findViewById(R.id.animation_circular_btn);

        final TextView mTxt = (TextView) findViewById(R.id.animation_circular_textView);

        assert mBtnStart != null;
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag){

                    //ViewAnimationUtils 是Android 5.0后引入的，使用需要判断版本
                    if(Build.VERSION.SDK_INT >= 21){
                        Animator animator =
                                ViewAnimationUtils.createCircularReveal(mTxt, mTxt.getWidth()/2, mTxt.getHeight()/2, Math.max(mTxt.getWidth()/2, mTxt.getHeight()/2) * 3 /2, 0);
                        animator.setDuration(3000);
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);

                                mTxt.setVisibility(View.INVISIBLE);
                            }
                        });
                        animator.start();

                    }

                    flag = false;
                }else{

                    //ViewAnimationUtils 是Android 5.0后引入的，使用需要判断版本
                    if(Build.VERSION.SDK_INT >= 21){
                        Animator animator =
                                ViewAnimationUtils.createCircularReveal(mTxt, mTxt.getWidth()/2, mTxt.getHeight()/2, 0, Math.max(mTxt.getWidth()/2, mTxt.getHeight()/2) * 3 /2);
                        animator.setDuration(3000);
                        animator.addListener(new AnimatorListenerAdapter() {

                            @Override
                            public void onAnimationStart(Animator animation) {
                                super.onAnimationStart(animation);
                                mTxt.setVisibility(View.VISIBLE);
                            }
                        });
                        animator.start();

                    }

                    flag = true;

                }

            }
        });




    }
}
