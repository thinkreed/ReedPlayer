package com.reed.reedplayer.component;

import android.media.MediaPlayer;
import android.util.Log;

import com.reed.reedplayer.ReedApplication;
import com.reed.reedplayer.model.Model;
import com.reed.reedplayer.utils.Consts;

/**
 * Created by thinkreed on 16/5/25.
 */
public class AudioPlayer
    implements
      MediaPlayer.OnPreparedListener,
      MediaPlayer.OnCompletionListener {

  public static final int STATE_PLAYING = 0;
  public static final int STATE_PAUSED = 1;
  public static final int STATE_STOPED = 2;
  private MediaPlayer mMediaPlayer;
  private boolean isReady;
  private OnPlayingStateChangedListener mPlayingStateChangedListener;

  @Override
  public void onCompletion(MediaPlayer mp) {
    next();
  }

  public interface OnPlayingStateChangedListener {
    void onPlayingStateChanged(int state);

    void onSongChanged(Model newSong);
  }

  public AudioPlayer(Model firstSong) {
    mMediaPlayer = new MediaPlayer();
    mMediaPlayer.setOnPreparedListener(this);
    mMediaPlayer.setOnCompletionListener(this);
    ReedApplication.getInstance().getQueueManager().setCurrentIndex(firstSong);
    reset(firstSong.song.path);
  }

  public void togglePlayingState() {
    if (isReady() && mPlayingStateChangedListener != null) {
      if (mMediaPlayer.isPlaying()) {
        mMediaPlayer.pause();
        mPlayingStateChangedListener.onPlayingStateChanged(STATE_PAUSED);
      } else {
        mMediaPlayer.start();
        mPlayingStateChangedListener.onPlayingStateChanged(STATE_PLAYING);
      }
    }
  }

  public void release() {
    mMediaPlayer.stop();
    mMediaPlayer.release();
    mMediaPlayer = null;
    isReady = false;
    mPlayingStateChangedListener = null;
  }

  private void reset(String path) {
    try {
      mMediaPlayer.stop();
      mMediaPlayer.reset();
      mMediaPlayer.setDataSource(path);
      mMediaPlayer.prepareAsync();
    } catch (Exception e) {
      Log.e(Consts.TAG, e.getMessage());
    }
  }

  public boolean isReady() {
    return isReady;
  }

  public void next() {
    Model nextSong = ReedApplication.getInstance().getQueueManager().next();
    if (nextSong != null) {
      reset(nextSong.song.path);
      if (mPlayingStateChangedListener != null) {
        mPlayingStateChangedListener.onSongChanged(nextSong);
      }
    }
  }

  public void previous() {
    Model prevSong = ReedApplication.getInstance().getQueueManager().prev();
    if (prevSong != null) {
      reset(prevSong.song.path);
      if (mPlayingStateChangedListener != null) {
        mPlayingStateChangedListener.onSongChanged(prevSong);
      }
    }
  }

  public void setPlayingStateChangedListener(OnPlayingStateChangedListener l) {
    mPlayingStateChangedListener = l;
  }

  @Override
  public void onPrepared(MediaPlayer mp) {
    isReady = true;
    togglePlayingState();
  }

  public int getCurrentPosition() {
    return mMediaPlayer != null ? mMediaPlayer.getCurrentPosition() : 0;
  }

  public void seekTo(int position) {
    if (mMediaPlayer != null) {
      mMediaPlayer.seekTo(position);
    }
  }
}
