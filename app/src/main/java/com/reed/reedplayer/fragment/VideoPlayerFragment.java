package com.reed.reedplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.reed.reedplayer.R;

/**
 * Created by thinkreed on 16/7/22.
 */
public class VideoPlayerFragment extends BaseFragment {

  private EMVideoView mVideoView;

  public static VideoPlayerFragment newInstance(Bundle bundle) {
    VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();
    videoPlayerFragment.setArguments(bundle);
    return videoPlayerFragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_video_player, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mVideoView = (EMVideoView) view.findViewById(R.id.video_view);
    mVideoView.setOnPreparedListener(new OnPreparedListener() {
      @Override
      public void onPrepared() {
        mVideoView.start();
      }
    });
    mVideoView.setVideoPath("http://198.199.113.13:8080/hlsLive/test.m3u8");
  }
}
