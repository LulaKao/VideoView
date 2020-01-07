package com.example.videoviewtest2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnErrorListener,MediaPlayer.OnCompletionListener {
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = new VideoView(this);
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController); // 把 MediaController 設定給 VideoView
        setContentView(videoView);

        // 設定 VideoView 要播放的影片檔
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoView.setVideoURI(uri);

        // 設定 VideoView 的 Callback
        videoView.setOnCompletionListener(this);
        videoView.setOnErrorListener(this);
    }

    @Override
    protected void onResume() {
        videoView.start();
        super.onResume();
    }

    @Override
    protected void onPause() {
        videoView.stopPlayback();
        super.onPause();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Toast.makeText(this,"播放完畢！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(this,"發生錯誤！",Toast.LENGTH_SHORT).show();
        return true;
    }
}
