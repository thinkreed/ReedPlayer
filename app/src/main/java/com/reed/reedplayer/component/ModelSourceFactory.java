package com.reed.reedplayer.component;

import android.content.res.Resources;

import com.reed.reedplayer.model.BaseModelSource;
import com.reed.reedplayer.model.LocalSongsModelSource;
import com.reed.reedplayer.utils.SourceType;

/**
 * Created by thinkreed on 16/7/12.
 */
public class ModelSourceFactory {
  public static BaseModelSource createModelSource(SourceType type) {
    if (type == null) {
      throw new NullPointerException("type can not be null!");
    }
    switch (type) {
      case LOCAL_MUSIC:
        return new LocalSongsModelSource();
      case LOCAL_VIDEO:
        return null;
      default:
        throw new Resources.NotFoundException("no found this type source");
    }
  }
}
