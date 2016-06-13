package com.lpd.android;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lpd.android.animation.AnimationActivity;
import com.lpd.android.fuheview.BtnEdit;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty);

        BtnEdit edt = (BtnEdit) findViewById(R.id.id_btnEdit);


//        LruCache

    }


    /**
     * Animation 动画
     * @param source
     */
    public void animation(View source){

        Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
        startActivity(intent);

        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f);
        PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0f,1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f);

        ObjectAnimator.ofPropertyValuesHolder(null, scaleX,scaleY).setDuration(2000).start();

    }
}
