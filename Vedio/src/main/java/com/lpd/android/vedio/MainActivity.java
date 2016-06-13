package com.lpd.android.vedio;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder recorder;
    private String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initVideo();

    }

    public void start(View source){

        initRecorder();

        recorder.start();


    }


    public void stop(View source)throws IOException,ClassCastException{

        recorder.stop();
        recorder.reset();   // You can reuse the object by going back to setAudioSource() step
        recorder.release(); // Now the object cannot be reused

        

    }

    public void play(View source){

        MediaPlayer player = new MediaPlayer();

        try {

            player.setDataSource(videoPath);
            player.prepare();
            player.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void initRecorder() {

        videoPath = Environment.getExternalStorageDirectory().getPath();
        videoPath = videoPath +"/"+"video";

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recorder.setOutputFile(videoPath);
        try {
            recorder.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }

        recorder.start();   // Recording is now started




    }

    private void initVideo() {

        VideoView mVideoView = (VideoView) findViewById(R.id.id_videoView);
        mVideoView.setVideoURI(Uri.parse("http://169.254.61.202:8080/MyPaoT/video/8Ojw2mUwBIV.10201.3.mp4"));
        MediaController controller= new MediaController(this,true);
        mVideoView.setMediaController(controller);
        mVideoView.start();

    }
}
