package com.reed.reedplayer.interfaces;

/**
 * Created by thinkreed on 16/7/23.
 */
public interface MediaController {
  void togglePlay();
  void seekTo(int milliSeconds);
  void next();
  void prev();
  void stop();
}
