package com.lpd.android.animation;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DrawableUtils;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.lpd.android.R;

public class AnimationDrawable extends AppCompatActivity {

    private android.graphics.drawable.AnimationDrawable animationDrawable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_drawable);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void start(View source){

        ImageView imageView = (ImageView) findViewById(R.id.animation_drawable_img);

        int duration = 300;

        animationDrawable = new android.graphics.drawable.AnimationDrawable();

        animationDrawable.addFrame(getDrawable(R.mipmap.adward_box_wait),duration);
        animationDrawable.addFrame(getDrawable(R.mipmap.adward_box_1),duration);
        animationDrawable.addFrame(getDrawable(R.mipmap.adward_box_2),duration);
        animationDrawable.addFrame(getDrawable(R.mipmap.adward_box_3),duration);
        animationDrawable.addFrame(getDrawable(R.mipmap.adward_box_4),duration);
        animationDrawable.addFrame(getDrawable(R.mipmap.adward_box_5),duration);
        animationDrawable.setOneShot(false);

        imageView.setBackgroundDrawable(animationDrawable);
        animationDrawable.start();

    }

    public void stop(View source){
        animationDrawable.stop();
    }
}
