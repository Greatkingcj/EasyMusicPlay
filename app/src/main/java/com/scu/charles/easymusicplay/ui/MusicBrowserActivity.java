package com.scu.charles.easymusicplay.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.scu.charles.easymusicplay.R;
import com.scu.charles.easymusicplay.adapter.MusicBrowserAdapter;
import com.scu.charles.easymusicplay.app.MyApplication;
import com.scu.charles.easymusicplay.model.Audio;
import com.scu.charles.easymusicplay.service.MusicPlayerService;
import com.scu.charles.easymusicplay.utils.MediaUtils;

import java.util.ArrayList;
import java.util.List;

public class MusicBrowserActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView mListview;
    MusicBrowserAdapter musicBrowserAdapter;
    List<Audio> mData;
    private MyApplication app;
    boolean isPlaying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_browser);
        app = (MyApplication) getApplication();
        initViews();
    }



    private void initViews() {

        mListview = (RecyclerView) findViewById(R.id.rl_music_list);
        mData = new ArrayList<Audio>();
        mData = MediaUtils.getAudioList(MusicBrowserActivity.this);
        musicBrowserAdapter = new MusicBrowserAdapter(mData);
        mListview.setLayoutManager(new LinearLayoutManager(MusicBrowserActivity.this));
        mListview.setAdapter(musicBrowserAdapter);
        musicBrowserAdapter.setOnItemClickListener(new MusicBrowserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("TAG",mData.get(position).getPath());
                MediaPlayer mediaPlayer = new MediaPlayer();
                try{
                    mediaPlayer.setDataSource(mData.get(position).getPath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
/*
                if(!isPlaying) {
                    app.bindMusicService();
                    app.musicPlayerService.start(mData.get(position).getPath());
                    isPlaying = true;
                }else{
                    app.unbindMusicService();
                }
*/

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.iv_pre:
            break;
            case R.id.iv_next:

                break;
            case R.id.iv_control:
                /*if(!isPlaying){
                    app.bindMusicService();
                    app.musicPlayerService.start(mData.get(position).getPath());
                    isPlaying = true;
                }*/
                break;
        }
    }
}
