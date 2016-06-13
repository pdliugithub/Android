package com.lpd.android.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lpd.android.myview.customview.AvoidXfermodeView;
import com.lpd.android.myview.customview.PorterDuffXfermodeView;
import com.lpd.android.myview.customview.PoterDuffLoadingView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


    }
}
