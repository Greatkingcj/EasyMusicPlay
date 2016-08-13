package com.scu.charles.easymusicplay.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;

import com.scu.charles.easymusicplay.constant.State;
import com.scu.charles.easymusicplay.model.Audio;

import java.io.IOException;

public class MusicPlayerService extends Service implements
        MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener,MediaPlayer.OnErrorListener {
    private MediaPlayer mPlayer;
    private int mCurrentState;
    public MusicPlayerService() {
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        doStart();
    }

    public static class ServiceBinder extends Binder {
        private MusicPlayerService mService = null;

        public ServiceBinder(MusicPlayerService service){
            mService = service;
        }

        public MusicPlayerService getService(){
            return mService;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    private void init(){
        if(mPlayer==null){
            mPlayer = new MediaPlayer();
            changeState(State.IDLE);
        }else{
            if(mCurrentState== State.IDLE||mCurrentState==State.INITIALIZED||
                    mCurrentState==State.PREPARED||mCurrentState==State.STARTED||
                    mCurrentState==State.PAUSED||mCurrentState==State.STOPPED||
                    mCurrentState==State.COMPLETED||mCurrentState==State.ERROR){
                mPlayer.reset();
                changeState(State.IDLE);
            }
        }
        mPlayer.setOnErrorListener(this);       //MainService 要实现这三个接口
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnCompletionListener(this);
    }
    
    public void start(String path){
        init();
        try{
            if(mCurrentState == State.IDLE){
                mPlayer.setDataSource(path);
            }
            changeState(State.INITIALIZED);
            if(mCurrentState != State.ERROR){
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            }
            if(mCurrentState == State.INITIALIZED || mCurrentState == State.STOPPED){
                mPlayer.prepareAsync();
                changeState(State.PREPARED);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doStart(){
        mPlayer.start();
        changeState(State.STARTED);
    }

    private void changeState(int state) {
        mCurrentState = state;
        if(mPlaybackListener != null){
            mPlaybackListener.onStateChanged(mCurrentAudio,mCurrentState);
        }
    }
    public Audio mCurrentAudio;
    public OnPlaybackListener mPlaybackListener;

    
    public void setOnPlaybackListener(OnPlaybackListener listener){
        mPlaybackListener = listener;
    }

    public static interface OnPlaybackListener{
        public void onStateChanged(Audio source,int state);
    }

}
