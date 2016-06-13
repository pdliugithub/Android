package com.lpd.android.camera;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView mSurfaceView;
    private Camera mCamera;
    private SurfaceHolder mSurfaceHolder;

    private Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, final Camera camera) {

            final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.id_operate);
            relativeLayout.setAnimation(AnimationUtils.loadAnimation(CameraActivity.this, R.anim.anim));
            relativeLayout.setVisibility(ViewGroup.VISIBLE);

            Button btnSave = (Button) relativeLayout.findViewById(R.id.id_btn_save);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    camera.startPreview();

                    relativeLayout.setVisibility(View.GONE);
                }
            });
            Button btnCancel = (Button) relativeLayout.findViewById(R.id.id_btn_cancel);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    camera.startPreview();

                    relativeLayout.setVisibility(View.GONE);
                }
            });


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mSurfaceView = (SurfaceView) findViewById(R.id.id_surfaceView);


        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);

        mCamera = Camera.open();

        /**
         * 点击SurfaceView时自动对焦
         */
        mSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCamera.autoFocus(null);

            }
        });

    }

    /**
     * 拍照
     *
     * @param source
     */
    public void capture(View source) {

        //设置参数
        Camera.Parameters parameters = mCamera.getParameters();
        //设置自动对焦
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        //设置图片格式
        parameters.setPictureFormat(ImageFormat.JPEG);
        //设置
        parameters.setPictureSize(400, 800);

        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {

                if (success) {
                    mCamera.takePicture(null, null, mPictureCallback);
                }

            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        onMyStart(mCamera, mSurfaceHolder);
    }

    @Override
    protected void onPause() {
        super.onPause();
        onMyRelease();
    }

    public void onMyStart(Camera camera, SurfaceHolder holder) {

        if (camera == null) {
            mCamera = Camera.open();
        }

        if (holder == null) {
            return;
        }
        try {
            //Camera与Surface
            mCamera.setPreviewDisplay(holder);
            //startPreview
            mCamera.startPreview();
            //设置mCamera预览角度
            mCamera.setDisplayOrientation(90);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 释放资源
     */
    public void onMyRelease() {

        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        onMyStart(mCamera, holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mCamera.stopPreview();
        onMyStart(mCamera, holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        onMyRelease();
    }
}
