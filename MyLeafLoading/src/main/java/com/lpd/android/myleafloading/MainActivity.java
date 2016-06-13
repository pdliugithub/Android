package com.lpd.android.myleafloading;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.lpd.android.myleafloading.utils.ImageRadarView;
import com.lpd.android.myleafloading.utils.LeafLoadingView;
import com.lpd.android.myleafloading.utils.WaterProgress;

public class MainActivity extends AppCompatActivity {

    private LeafLoadingView mLeafLoading;
    private boolean isLoading = true;
    private int i =0;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            if(msg.what == 0x134){

                mLeafLoading.setProgress(i);

            }

        }
    };
    private ImageRadarView mRadarView;
    private WaterProgress mWaterProgess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mLeafLoading = (LeafLoadingView)findViewById(R.id.id_leafLoading);
        mRadarView = (ImageRadarView)findViewById(R.id.id_raderView);

        mRadarView.startScan();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while(isLoading){

                    i++;

                    if(i == 100){
                        isLoading = false;
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.sendEmptyMessage(0x134);


                }


            }
        }).start();

    }
}
