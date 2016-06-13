package com.lpd.viewflipper;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnPre,btnNext;
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        Animation inAnim = AnimationUtils.loadAnimation(this,R.anim.in_animation);
        inAnim.setDuration(2000);
        Animation outAnim = AnimationUtils.loadAnimation(this,R.anim.out_animation);
        outAnim.setDuration(2000);
        //设置进入动画
        viewFlipper.setInAnimation(inAnim);
        //设置退出动画
        viewFlipper.setOutAnimation(outAnim);

        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "My name is lpd ,....", Toast.LENGTH_SHORT).show();
            }
        });
        btnPre = (Button) findViewById(R.id.pre);
        btnPre.setOnClickListener(this);
        btnNext = (Button) findViewById(R.id.next);
        btnNext.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v == btnPre){

            viewFlipper.showPrevious();
        }else if(v == btnNext){

            viewFlipper.showNext();
        }


    }
}
