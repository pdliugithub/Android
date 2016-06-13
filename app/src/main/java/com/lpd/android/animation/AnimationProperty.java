package com.lpd.android.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lpd.android.R;

import java.util.ArrayList;
import java.util.List;

public class AnimationProperty extends AppCompatActivity implements View.OnClickListener{

    private int[] imgIds = {R.id.animation_property_img_a
            , R.id.animation_property_img_b
            , R.id.animation_property_img_c
            , R.id.animation_property_img_d
            , R.id.animation_property_img_e
            , R.id.animation_property_img_f
    };

    private List<ImageView> imgLists = new ArrayList<>();

    private boolean flag = false;

    private Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_property);

        Button property_start = (Button) findViewById(R.id.animation_property_start);

        property_start.animate().rotationX(200);
        for (int i=0;i<imgIds.length ; i++){

            ImageView imageView = (ImageView) findViewById(imgIds[i]);
            imageView.setOnClickListener(this);
            imgLists.add(imageView);
        }

        property_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

    }

    private void startAnimation() {

        ImageView imageView = (ImageView) findViewById(R.id.animation_property_img);

        ObjectAnimator translation = ObjectAnimator.ofFloat(imageView, "translationX", 0, 200).setDuration(2000);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360).setDuration(2000);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 0, 1).setDuration(2000);

        AnimatorSet set = new AnimatorSet();

        set.play(translation).with(alpha);
        set.play(rotation).after(alpha);
        set.setDuration(2000);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        set.start();

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.animation_property_img_a:

                if(flag){

                    stopPropertyAnim();
                    flag = false;
                }else{
                    startPropertyAnim();
                    flag = true;
                }

                break;

            default:

                if(toast != null){
                    toast.setText("click"+v.getId());
                }else{
                    toast = Toast.makeText(AnimationProperty.this, "click"+v.getId(), Toast.LENGTH_SHORT);
                }

                toast.show();
                break;

        }


    }

    private void stopPropertyAnim() {

        for (int i = 0; i < imgLists.size(); i++) {

            ObjectAnimator translationX = ObjectAnimator.ofFloat(imgLists.get(i), "translationX", ( i * 50) + 20, 0);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(imgLists.get(i), "translationY", ((i * i) * (i * i)) / 2f, 0);

            AnimatorSet set = new AnimatorSet();
            set.play(translationX).with(translationY);
            set.setDuration(2000);
            set.setInterpolator(new BounceInterpolator());
            set.start();
        }

    }

    private void startPropertyAnim() {

        for (int i = 0; i < imgLists.size(); i++) {

            ObjectAnimator translationX = ObjectAnimator.ofFloat(imgLists.get(i), "translationX",  0, ( i * 50) + 20);
            ObjectAnimator translationY = ObjectAnimator.ofFloat(imgLists.get(i), "translationY",  0, ((i * i) * (i * i)) / 2f);

            AnimatorSet set = new AnimatorSet();
            set.play(translationX).with(translationY);
            set.setDuration(2000);
            set.setInterpolator(new BounceInterpolator());
            set.start();


        }



    }
}
