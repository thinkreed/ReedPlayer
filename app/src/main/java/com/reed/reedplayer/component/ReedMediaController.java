package com.reed.reedplayer.component;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.devbrackets.android.exomedia.core.api.VideoViewApi;
import com.reed.reedplayer.R;

/**
 * Created by thinkreed on 16/7/23.
 */
public class ReedMediaController {

  private VideoViewApi videoViewApiImpl;
  private ImageView btnPlayPause;
  private SeekBar seekBar;
  private View btnNext;
  private View btnPrev;
  private Handler progressHandler;

  private ReedMediaController(Builder builder) {
    videoViewApiImpl = builder.videoViewApiImpl;
    btnPlayPause = builder.btnPlayPause;
    seekBar = builder.seekBar;
    btnNext = builder.btnNext;
    btnPrev = builder.btnPrev;
    progressHandler = builder.progressHandler;
    setUp();
  }

  private void setUp() {
    if (videoViewApiImpl == null) {
      throw new RuntimeException("videoViewApiImpl can not be null!");
    }
    if (progressHandler == null) {
      throw new RuntimeException("progressHandler can not be null");
    }
    if (seekBar != null) {
      seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          if (fromUser) {
            videoViewApiImpl.seekTo(progress * 1000);
          }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
      });
    }
    if (btnNext != null) {
      btnNext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
      });
    }
    if (btnPlayPause != null) {
      btnPlayPause.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (videoViewApiImpl.isPlaying()) {
            videoViewApiImpl.pause();
            btnPlayPause.setImageDrawable(ContextCompat.getDrawable(v.getContext(),
                R.drawable.uamp_ic_play_arrow_white_24dp));
            progressHandler.removeCallbacksAndMessages(null);
          } else {
            videoViewApiImpl.start();
            btnPlayPause.setImageDrawable(ContextCompat.getDrawable(v.getContext(),
                R.drawable.uamp_ic_pause_white_24dp));
            progressHandler.post(new RefreshTask());
          }
        }
      });
    }
    if (btnPrev != null) {
      btnPrev.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
      });
    }
  }

  public void start() {
    if (btnPlayPause != null) {
      videoViewApiImpl.start();
      seekBar.setProgress(0);
      seekBar.setMax(videoViewApiImpl.getDuration()/1000);
      progressHandler.post(new RefreshTask());
      btnPlayPause.setImageDrawable(ContextCompat.getDrawable(btnPlayPause.getContext(),
          R.drawable.uamp_ic_pause_white_24dp));
    }
  }

  public void stop() {
    if (videoViewApiImpl.isPlaying()) {
      videoViewApiImpl.stopPlayback();
    }
    videoViewApiImpl.release();
    videoViewApiImpl = null;
    progressHandler.removeCallbacksAndMessages(null);
    progressHandler = null;
    btnPlayPause = null;
    btnPrev = null;
    btnNext = null;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static Builder newBuilder(ReedMediaController copy) {
    Builder builder = new Builder();
    builder.videoViewApiImpl = copy.videoViewApiImpl;
    builder.btnPlayPause = copy.btnPlayPause;
    builder.seekBar = copy.seekBar;
    builder.btnNext = copy.btnNext;
    builder.btnPrev = copy.btnPrev;
    builder.progressHandler = copy.progressHandler;
    return builder;
  }

  class RefreshTask implements Runnable {
    @Override
    public void run() {
      seekBar.setProgress(videoViewApiImpl.getCurrentPosition() / 1000);
      progressHandler.postDelayed(this, 1000);
    }
  }

  /**
   * {@code ReedMediaController} builder static inner class.
   */
  public static final class Builder {
    private VideoViewApi videoViewApiImpl;
    private ImageView btnPlayPause;
    private SeekBar seekBar;
    private View btnNext;
    private View btnPrev;
    private Handler progressHandler;

    private Builder() {}

    /**
     * Sets the {@code videoViewApiImpl} and returns a reference to this Builder so that the methods
     * can be chained together.
     *
     * @param val the {@code videoViewApiImpl} to set
     * @return a reference to this Builder
     */
    public Builder videoViewApiImpl(VideoViewApi val) {
      videoViewApiImpl = val;
      return this;
    }

    /**
     * Sets the {@code btnPlayPause} and returns a reference to this Builder so that the methods can
     * be chained together.
     *
     * @param val the {@code btnPlayPause} to set
     * @return a reference to this Builder
     */
    public Builder btnPlayPause(ImageView val) {
      btnPlayPause = val;
      return this;
    }

    /**
     * Sets the {@code seekBar} and returns a reference to this Builder so that the methods can be
     * chained together.
     *
     * @param val the {@code seekBar} to set
     * @return a reference to this Builder
     */
    public Builder seekBar(SeekBar val) {
      seekBar = val;
      return this;
    }

    /**
     * Sets the {@code btnNext} and returns a reference to this Builder so that the methods can be
     * chained together.
     *
     * @param val the {@code btnNext} to set
     * @return a reference to this Builder
     */
    public Builder btnNext(View val) {
      btnNext = val;
      return this;
    }

    /**
     * Sets the {@code btnPrev} and returns a reference to this Builder so that the methods can be
     * chained together.
     *
     * @param val the {@code btnPrev} to set
     * @return a reference to this Builder
     */
    public Builder btnPrev(View val) {
      btnPrev = val;
      return this;
    }

    /**
     * Sets the {@code progressHandler} and returns a reference to this Builder so that the methods
     * can be chained together.
     *
     * @param val the {@code progressHandler} to set
     * @return a reference to this Builder
     */
    public Builder progressHandler(Handler val) {
      progressHandler = val;
      return this;
    }

    /**
     * Returns a {@code ReedMediaController} built from the parameters previously set.
     *
     * @return a {@code ReedMediaController} built with parameters of this
     *         {@code ReedMediaController.Builder}
     */
    public ReedMediaController build() {
      return new ReedMediaController(this);
    }
  }
}
