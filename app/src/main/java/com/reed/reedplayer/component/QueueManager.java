package com.reed.reedplayer.component;

import com.reed.reedplayer.model.Model;
import com.reed.reedplayer.utils.CheckUtils;

import java.util.List;

/**
 * Created by thinkreed on 16/5/25.
 */
public class QueueManager {
  private List<Model> mPlayList;

  private int mCurrentIndex;

  public Model next() {
    return CheckUtils.isEmpty(mPlayList)
        ? null
        : mPlayList.get(++mCurrentIndex % mPlayList.size());
  }

  public Model prev() {
    if (CheckUtils.isEmpty(mPlayList)) {
      return null;
    }
    if (mCurrentIndex == 0) {
      mCurrentIndex = mPlayList.size() - 1;
      return mPlayList.get(mCurrentIndex);
    }
    return mPlayList.get(--mCurrentIndex);
  }

  public void setPlaylist(List<Model> playlist) {
    this.mPlayList = playlist;
  }

  public void setCurrentIndex(Model currentSong) {
    if (currentSong != null) {
      int index = -1;
      for (int i = 0; i < mPlayList.size(); i++) {
        if (mPlayList.get(i).song.id.equals(currentSong.song.id)) {
          index = i;
        }
      }
      if (index != -1) {
        this.mCurrentIndex = index;
      }
    }
  }
}
