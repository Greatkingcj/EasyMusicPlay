package com.scu.charles.easymusicplay.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import com.scu.charles.easymusicplay.model.Audio;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charles on 2016/8/9.
 */
public class MediaUtils {
    public static final String[] AUDIO_KEYS = new String[]{
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.TITLE_KEY,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.ARTIST_KEY,
            MediaStore.Audio.Media.COMPOSER,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ALBUM_KEY,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media.YEAR,
            MediaStore.Audio.Media.TRACK,
            MediaStore.Audio.Media.IS_RINGTONE,
            MediaStore.Audio.Media.IS_PODCAST,
            MediaStore.Audio.Media.IS_ALARM,
            MediaStore.Audio.Media.IS_MUSIC,
            MediaStore.Audio.Media.IS_NOTIFICATION,
            MediaStore.Audio.Media.MIME_TYPE,
            MediaStore.Audio.Media.DATA
    };

    public static List<Audio> getAudioList(Context context){
        List<Audio> audioList = new ArrayList<Audio>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                AUDIO_KEYS,
                null,
                null,
                null);

        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            Bundle bundle = new Bundle();
            for(int i=0;i<AUDIO_KEYS.length;i++){
                final String key = AUDIO_KEYS[i];
                final int columnIndex = cursor.getColumnIndex(key);
                final int type = cursor.getType(columnIndex);
                switch (type){
                    case Cursor.FIELD_TYPE_BLOB:
                        break;
                    case Cursor.FIELD_TYPE_FLOAT:
                        float floatValue = cursor.getFloat(columnIndex);
                        bundle.putFloat(key, floatValue);
                        break;
                    case Cursor.FIELD_TYPE_INTEGER:
                        int intValue = cursor.getInt(columnIndex);
                        bundle.putInt(key, intValue);
                        break;
                    case Cursor.FIELD_TYPE_NULL:
                        break;
                    case Cursor.FIELD_TYPE_STRING:
                        String strValue = cursor.getString(columnIndex);
                        bundle.putString(key, strValue);
                        break;
                }
            }
            Audio audio = new Audio(bundle);
            audioList.add(audio);
        }
        cursor.close();
        return audioList;
    }

    public static List<String> getArtPicList(Context context,String albumKey){
        List<String> picList = new ArrayList<>();
        String[] argArr = {albumKey};
        ContentResolver albumResolver = context.getContentResolver();
        Cursor albumCursor = albumResolver.query(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                null,
                MediaStore.Audio.AlbumColumns.ALBUM_KEY + " = ?",
                argArr,
                null);
        if(null!=albumCursor&&albumCursor.getCount()>0){
            for(albumCursor.moveToFirst();!albumCursor.isAfterLast();albumCursor.moveToNext()) {
                int albumArtIndex = albumCursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ART);
                String musicAlbumArtPath = albumCursor.getString(albumArtIndex);
                if (null != musicAlbumArtPath
                        && !"".equals(musicAlbumArtPath)) {
                    picList.add(musicAlbumArtPath);
                }
            }
        }
        albumCursor.close();
        return picList;
    }
}
