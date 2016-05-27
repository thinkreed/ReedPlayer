package com.reed.reedplayer.utils;

import java.util.Collection;

/**
 * Created by thinkreed on 16/5/23.
 */
public class CheckUtils {

  public static <T> T get(T value, T defaultValue) {
    return value != null ? value : defaultValue;
  }

  public static boolean isEmpty(Collection collection) {
    return collection == null || collection.size() == 0;
  }
}
