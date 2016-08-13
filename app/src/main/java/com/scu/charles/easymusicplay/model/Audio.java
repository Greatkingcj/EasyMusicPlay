package com.scu.charles.easymusicplay.model;

import android.os.Bundle;
import android.provider.MediaStore;

/**
 * Created by charles on 2016/8/9.
 */
public class Audio {
    //歌曲的名称
    private String mTitle;
    private String mTitleKey;
    //歌曲的歌手名
    private String mArtist;
    private String mArtistKey;
    private String mComposer;
    //歌曲的专辑名
    private String mAlbum;
    private String mAlbumKey;
    private String mDisplayName;
    private String mMimeType;
    //歌曲的全路径
    private String mPath;

    private int mId;
    private int mArtistId;
    private int mAlbumId;
    private int mYear;
    private int mTrack;
    //歌曲的播放时长
    private int mDuration=0;
    //歌曲文件的大小
    private int mSize = 0;

    private boolean isRingtone = false;
    private boolean isPodcast = false;
    private boolean isAlarm = false;
    private boolean isMusic = false;
    private boolean isNotification = false;

    public Audio(Bundle bundle){
        mId = bundle.getInt(MediaStore.Audio.Media._ID);
        mTitle = bundle.getString(MediaStore.Audio.Media.TITLE);
        mTitleKey = bundle.getString(MediaStore.Audio.Media.TITLE_KEY);
        mArtist = bundle.getString(MediaStore.Audio.Media.ARTIST);
        mArtistKey = bundle.getString(MediaStore.Audio.Media.ARTIST_KEY);
        mComposer = bundle.getString(MediaStore.Audio.Media.COMPOSER);
        mAlbum = bundle.getString(MediaStore.Audio.Media.ALBUM);
        mAlbumKey = bundle.getString(MediaStore.Audio.Media.ALBUM_KEY);
        mDisplayName = bundle.getString(MediaStore.Audio.Media.DISPLAY_NAME);
        mYear = bundle.getInt(MediaStore.Audio.Media.YEAR);
        mMimeType = bundle.getString(MediaStore.Audio.Media.MIME_TYPE);
        mPath = bundle.getString(MediaStore.Audio.Media.DATA);

        mArtistId = bundle.getInt(MediaStore.Audio.Media.ARTIST_ID);
        mAlbumId = bundle.getInt(MediaStore.Audio.Media.ALBUM_ID);
        mTrack = bundle.getInt(MediaStore.Audio.Media.TRACK);
        mDuration = bundle.getInt(MediaStore.Audio.Media.DURATION);
        mSize = bundle.getInt(MediaStore.Audio.Media.SIZE);
        isRingtone = bundle.getInt(MediaStore.Audio.Media.IS_RINGTONE) == 1;
        isPodcast = bundle.getInt(MediaStore.Audio.Media.IS_PODCAST) == 1;
        isAlarm = bundle.getInt(MediaStore.Audio.Media.IS_ALARM) == 1;
        isMusic = bundle.getInt(MediaStore.Audio.Media.IS_MUSIC) == 1;
        isNotification = bundle.getInt(MediaStore.Audio.Media.IS_NOTIFICATION) == 1;
    }

    public int getId() {
        return mId;
    }

    public String getMimeType () {
        return mMimeType;
    }

    public int getDuration () {
        return mDuration;
    }

    public int getSize () {
        return mSize;
    }

    public boolean isRingtone () {
        return isRingtone;
    }

    public boolean isPodcast () {
        return isPodcast;
    }

    public boolean isAlarm () {
        return isAlarm;
    }

    public boolean isMusic () {
        return isMusic;
    }

    public boolean isNotification () {
        return isNotification;
    }

    public String getTitle () {
        return mTitle;
    }

    public String getTitleKey () {
        return mTitleKey;
    }

    public String getArtist () {
        return mArtist;
    }

    public int getArtistId () {
        return mArtistId;
    }

    public String getArtistKey () {
        return mArtistKey;
    }

    public String getComposer () {
        return mComposer;
    }

    public String getAlbum () {
        return mAlbum;
    }

    public int getAlbumId () {
        return mAlbumId;
    }

    public String getAlbumKey () {
        return mAlbumKey;
    }

    public String getDisplayName () {
        return mDisplayName;
    }

    public int getYear () {
        return mYear;
    }

    public int getTrack () {
        return mTrack;
    }

    public String getPath () {
        return mPath;
    }
}
