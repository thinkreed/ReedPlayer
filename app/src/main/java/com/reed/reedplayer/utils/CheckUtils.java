package com.reed.reedplayer.utils;

/**
 * Created by thinkreed on 16/5/23.
 */
public class CheckUtils {

  public static <T> T get(T value, T defaultValue) {
    return value != null ? value : defaultValue;
  }
}
