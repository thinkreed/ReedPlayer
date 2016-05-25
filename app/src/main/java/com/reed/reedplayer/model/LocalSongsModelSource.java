package com.reed.reedplayer.model;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.reed.reedplayer.ReedApplication;
import com.reed.reedplayer.activity.PlayActivity;
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
        while (cur.moveToNext()) {
          Model model = new Model.Builder()
              .path(CheckUtils.get(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.DATA)),
                  Consts.EMPTY_STRING))
              .artist(
                  CheckUtils.get(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                      Consts.EMPTY_STRING))
              .title(
                  CheckUtils.get(cur.getString(cur.getColumnIndex(MediaStore.Audio.Media.TITLE)),
                      Consts.EMPTY_STRING))
              .templete(Model.Templete.ITEM_SONG)
              .motion(new Motion.Builder().clazz(PlayActivity.class).build())
              .build();
          models.add(model);
        }

      }
      cur.close();
    }
    return models;
  }
}
