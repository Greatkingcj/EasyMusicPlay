package com.scu.charles.easymusicplay.app;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Toast;

import com.scu.charles.easymusicplay.service.MusicPlayerService;

/**
 * Created by charles on 2016/8/9.
 */
public class MyApplication extends Application implements ServiceConnection{

    public   MusicPlayerService musicPlayerService;
    @Override
    public void onCreate() {
        super.onCreate();
        startMusicService();
        bindMusicService();
    }

    public void startMusicService(){
        Intent intent =new Intent(this, MusicPlayerService.class);
        startService(intent);
    }

    private  void stopMusicService(){
        Intent intent = new Intent(this,MusicPlayerService.class);
        stopService(intent);
    }

    public void bindMusicService(){
        Intent intent =new Intent(this, MusicPlayerService.class);
        this.bindService(intent,this, Service.BIND_AUTO_CREATE);
    }

    private void unbindMusicService(){
        Intent intent =new Intent(this, MusicPlayerService.class);
        this.unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        if(service instanceof MusicPlayerService.ServiceBinder){
            MusicPlayerService.ServiceBinder binder = (MusicPlayerService.ServiceBinder) service;
            musicPlayerService = binder.getService();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Toast.makeText(this,"onServiceDisconnnected",Toast.LENGTH_LONG).show();
    }
}
