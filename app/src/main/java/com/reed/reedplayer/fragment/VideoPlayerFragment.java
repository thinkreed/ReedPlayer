package com.reed.reedplayer.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.reed.reedplayer.R;
import com.reed.reedplayer.utils.Consts;

/**
 * Created by thinkreed on 16/7/22.
 */
public class VideoPlayerFragment extends BaseFragment
    implements
      View.OnClickListener,
      SeekBar.OnSeekBarChangeListener {

  private EMVideoView mVideoView;
  private ImageView mBtnPlayPause;
  private Handler mHandler;
  private SeekBar mProgress;

  public static VideoPlayerFragment newInstance(Bundle bundle) {
    VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();
    videoPlayerFragment.setArguments(bundle);
    return videoPlayerFragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_video_player, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mHandler = new Handler();
    mProgress = (SeekBar) view.findViewById(R.id.progress);
    mBtnPlayPause = (ImageView) view.findViewById(R.id.btn_play_pause);
    mVideoView = (EMVideoView) view.findViewById(R.id.video_view);
    mVideoView.setOnPreparedListener(new OnPreparedListener() {
      @Override
      public void onPrepared() {
        mVideoView.start();
        mProgress.setProgress(0);
        mProgress.setMax(mVideoView.getDuration() / 1000);
        mBtnPlayPause.setImageDrawable(
            ContextCompat.getDrawable(getActivity(), R.drawable.uamp_ic_pause_white_24dp));
        mHandler.post(new RefreshTask());
      }
    });
    String path = getArguments().getString(Consts.KEY_PATH);
    mBtnPlayPause.setOnClickListener(this);
    mProgress.setOnSeekBarChangeListener(this);
    mVideoView.setVideoPath(path);
  }

  private void togglePlay() {
    if (mVideoView.isPlaying()) {
      mVideoView.pause();
      mBtnPlayPause.setImageDrawable(
          ContextCompat.getDrawable(getActivity(), R.drawable.uamp_ic_play_arrow_white_24dp));
      mHandler.removeCallbacksAndMessages(null);
    } else {
      mVideoView.start();
      mBtnPlayPause.setImageDrawable(
          ContextCompat.getDrawable(getActivity(), R.drawable.uamp_ic_pause_white_24dp));
      mHandler.post(new RefreshTask());
    }
  }

  @Override
  public void onDestroyView() {
    mHandler.removeCallbacksAndMessages(null);
    mVideoView.stopPlayback();
    mVideoView.release();
    super.onDestroyView();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_play_pause:
        togglePlay();
        break;
      default:
        break;
    }
  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    if (mVideoView != null && fromUser) {
      mVideoView.seekTo(progress * 1000);
    }
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {

  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {

  }

  class RefreshTask implements Runnable {
    @Override
    public void run() {
      mProgress.setProgress(mVideoView.getCurrentPosition() / 1000);
      mHandler.postDelayed(this, 1000);
    }
  }
}
