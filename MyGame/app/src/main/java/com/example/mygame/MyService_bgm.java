package com.example.mygame;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MyService_bgm extends Service {
    MediaPlayer player;
    public IBinder onBind(Intent intent){return null;} //연결타입 서비스에서 사용함
    public void onCreate(){
        player = MediaPlayer.create(this,R.raw.bgm); //sample.mp3
        player.setLooping(true);//반복한다.
    }
    public void onDestroy(){
        Toast.makeText(getApplicationContext(),"Music Service Stop!",Toast.LENGTH_SHORT).show();
        player.stop(); //음악 연주 중지
    }
    public int onStartCommand(Intent intent,int flags,int startId){
        Toast.makeText(getApplicationContext(),"Music Service Start!",Toast.LENGTH_SHORT).show();
        player.start(); //음악 연주 시작
        return super.onStartCommand(intent,flags,startId);
    }
    public MyService_bgm() {
    }
}