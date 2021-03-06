package com.reed.reedplayer.model;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.reed.reedplayer.ReedApplication;
import com.reed.reedplayer.activity.MusicPlayerActivity;
import com.reed.reedplayer.utils.CheckUtils;
import com.reed.reedplayer.utils.Consts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkreed on 16/5/23.
 */
public class LocalSongsModelSource extends BaseModelSource {

  @Override
  protected List<Model> getModels() {
    List<Model> models = new ArrayList<>();
    ContentResolver cr = ReedApplication.getInstance().getContentResolver();

    Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
    String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
    Cursor cur = cr.query(uri, null, selection, null, sortOrder);
    int count = 0;

    if (cur != null) {
      count = cur.getCount();

      if (count > 0) {
        Uri artworkUri = Uri.parse("content://media/external/audio/albumart");
        while (cur.moveToNext()) {
          Song song = new Song.Builder()
              .path(CheckUtils.INSTANCE.get(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA)),
                      Consts.INSTANCE.getEMPTY_STRING()))
              .artist(
                  CheckUtils.INSTANCE.get(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                          Consts.INSTANCE.getEMPTY_STRING()))
              .title(CheckUtils.INSTANCE.get(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                      Consts.INSTANCE.getEMPTY_STRING()))
              .cover(ContentUris.withAppendedId(artworkUri, CheckUtils.INSTANCE
                  .get(cur.getLong(cur.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)), 0L)))
              .duration(CheckUtils.INSTANCE
                  .get(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DURATION)),
                          Consts.INSTANCE.getEMPTY_STRING()))
              .id(CheckUtils.INSTANCE.get(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media._ID)),
                      Consts.INSTANCE.getEMPTY_STRING()))
              .build();
          Model model = new Model.Builder()
              .song(song)
              .templete(Model.Templete.ITEM_SONG)
              .motion(new Motion.Builder().clazz(MusicPlayerActivity.class).build())
              .build();
          models.add(model);
        }

      }
      cur.close();
    }
    return models;
  }
}
