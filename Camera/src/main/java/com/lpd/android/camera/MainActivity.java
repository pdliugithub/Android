package com.lpd.android.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnCameraSys;
    private ImageView mImgCameraShow;
    private String mImgPath;
    private boolean isBack;
    private Button mBtnCameraCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnCameraSys = (Button) findViewById(R.id.id_btn_camera_system);
        mBtnCameraCustom = (Button)findViewById(R.id.id_btn_camera_custom);
        mImgCameraShow = (ImageView) findViewById(R.id.id_img_camera_show);

        mBtnCameraSys.setOnClickListener(this);
        mBtnCameraCustom.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.id_btn_camera_system:
                /**
                 * 调用系统相机
                 */
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mImgPath = Environment.getExternalStorageDirectory() + "/" + "temp";
                Uri uri = Uri.fromFile(new File(mImgPath));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                startActivityForResult(intent, 0);

                break;

            case R.id.id_btn_camera_custom:

                Intent intent1 = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent1);

                break;


        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 0) {


            Bitmap bitmap1 = BitmapFactory.decodeFile(mImgPath);

            mImgCameraShow.setImageBitmap(bitmap1);


        }

    }

}
