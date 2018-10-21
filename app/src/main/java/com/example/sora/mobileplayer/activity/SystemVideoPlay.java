package com.example.sora.mobileplayer.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.sora.mobileplayer.R;

/**
 * Created by sora on 2018/6/19.
 * 系统播放器
 */
public class SystemVideoPlay extends Activity{
    private VideoView videoView;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player);
        videoView = (VideoView) findViewById(R.id.videoview);

        //准备好的监听
        videoView.setOnPreparedListener(new MyOnPreparedListener());
        //播放出错的监听
        videoView.setOnErrorListener(new MyOnErrorListener());
        //播放完成的监听
        videoView.setOnCompletionListener(new MyOnCompletionListener());
        //得到播放地址
        uri = getIntent().getData();
        if(uri != null){
            videoView.setVideoURI(uri);
        }

        //设置控制面板
        videoView.setMediaController(new MediaController(this));
    }

    class MyOnPreparedListener implements MediaPlayer.OnPreparedListener{

        //当底层解码准备好的时候
        @Override
        public void onPrepared(MediaPlayer mp) {
            videoView.start();//开始播放
        }
    }

    class MyOnErrorListener implements MediaPlayer.OnErrorListener{

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            Toast.makeText(SystemVideoPlay.this, "播放出错了呀", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    class MyOnCompletionListener implements MediaPlayer.OnCompletionListener{

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(SystemVideoPlay.this, "播放完成了哟="+uri, Toast.LENGTH_SHORT).show();
        }
    }
}
