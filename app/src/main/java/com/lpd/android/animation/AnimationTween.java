package com.lpd.android.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.lpd.android.R;

public class AnimationTween extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_tween);


        button = (Button) findViewById(R.id.animation_tween_start);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

    }



    public void start(){

        ImageView imageView = (ImageView) findViewById(R.id.animation_tween_img);


        TranslateAnimation translate = new TranslateAnimation(0f, 200f, 0f, 0f);

        RotateAnimation rotate = new RotateAnimation(0f, 360f, 0, 0);

        AlphaAnimation alpha = new AlphaAnimation(0f, 1f);

        AnimationSet set =new AnimationSet(true);
        set.setDuration(3000);
        set.setFillAfter(true);
        set.addAnimation(translate);
        set.addAnimation(rotate);
        set.addAnimation(alpha);
        set.start();

        imageView.startAnimation(set);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                button.setText("Animation Start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                button.setText("Animation End");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}
