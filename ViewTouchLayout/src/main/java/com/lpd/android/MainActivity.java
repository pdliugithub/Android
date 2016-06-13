package com.lpd.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    float width,height;
    private Button mBtnTouchMove;
    private RelativeLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         mBtnTouchMove = (Button) findViewById(R.id.id_btn_touch_move);

          params = (RelativeLayout.LayoutParams) mBtnTouchMove.getLayoutParams();


        mBtnTouchMove.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                switch(event.getAction()){

                    case MotionEvent.ACTION_DOWN:

                        break;

                    case MotionEvent.ACTION_MOVE:

                        moveViewWithFinger(v, event.getRawX(), event.getRawY());
                        break;
                }


                return true;
            }
        });
    }

    private void moveViewWithFinger(View view, float rawX, float rawY) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
                .getLayoutParams();
        params.leftMargin = (int) rawX - mBtnTouchMove.getWidth() / 2 ;
        params.topMargin = (int) rawY - mBtnTouchMove.getHeight() / 2;
        view.setLayoutParams(params);
    }


}
