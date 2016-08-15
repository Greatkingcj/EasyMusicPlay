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
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
    private int currentPosition = 0;
    private ImageView music_pic;
    private ImageView iv_pre;
    private ImageView iv_next;
    private ImageView iv_control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_browser);
        app = (MyApplication) getApplication();
        initViews();
    }



    private void initViews() {
        iv_pre = (ImageView) findViewById(R.id.iv_pre);
        iv_pre.setOnClickListener(this);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        iv_next.setOnClickListener(this);
        iv_control = (ImageView) findViewById(R.id.iv_control);
        iv_control.setOnClickListener(this);
        music_pic = (ImageView) findViewById(R.id.music_pic);
        mListview = (RecyclerView) findViewById(R.id.rl_music_list);
        mData = new ArrayList<Audio>();
        mData = MediaUtils.getAudioList(MusicBrowserActivity.this);
        musicBrowserAdapter = new MusicBrowserAdapter(mData);
        mListview.setLayoutManager(new LinearLayoutManager(MusicBrowserActivity.this));
        mListview.setAdapter(musicBrowserAdapter);
        musicBrowserAdapter.setOnItemClickListener(new MusicBrowserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                currentPosition = position;
                app.musicPlayerService.start(mData.get(position).getPath());
                if(MediaUtils.getArtPicList(MusicBrowserActivity.this,mData.get(position).getAlbumKey()).size()>0) {
                    String url = MediaUtils.getArtPicList(MusicBrowserActivity.this, mData.get(position).getAlbumKey()).get(0);
                    Log.i("Tag",MediaUtils.getArtPicList(MusicBrowserActivity.this,mData.get(position).getAlbumKey()).size()+" kkk");
                    Log.i("Tag", url);
                    Glide.with(MusicBrowserActivity.this).load("file://" + url).into(music_pic);
                }else{
                    music_pic.setImageResource(R.mipmap.ic_launcher);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.iv_pre:
                if(currentPosition==0){
                    currentPosition = mData.size()-1;
                    app.musicPlayerService.start(mData.get(currentPosition).getPath());
                }else{
                    currentPosition = currentPosition -1;
                    app.musicPlayerService.start(mData.get(currentPosition-1).getPath());
                }
            break;
            case R.id.iv_next:
                if(currentPosition==mData.size()-1){
                    currentPosition = 0;
                    app.musicPlayerService.start(mData.get(currentPosition).getPath());
                }else{
                    currentPosition = currentPosition+1;
                    app.musicPlayerService.start(mData.get(currentPosition).getPath());
                }
                break;
            case R.id.iv_control:
                app.musicPlayerService.pause();
                break;
        }
    }
}
