package com.lpd.android.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lpd.android.R;

public class AnimationActivity extends AppCompatActivity {

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        mContext = AnimationActivity.this;
    }


    public void animationDrawable(View source) {
        Intent intent = new Intent(mContext, AnimationDrawable.class);
        startActivity(intent);

    }

    public void animationTween(View source) {
        Intent intent = new Intent(mContext, AnimationTween.class);
        startActivity(intent);


    }

    public void animationProperty(View source) {
        Intent intent = new Intent(mContext, AnimationProperty.class);
        startActivity(intent);

    }

    public void animationCircularReveal(View source){

        Intent intent = new Intent(mContext, AnimationCircularReveal.class);
        startActivity(intent);

    }

}
