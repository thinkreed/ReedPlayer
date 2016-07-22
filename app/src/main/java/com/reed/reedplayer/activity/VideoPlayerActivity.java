package com.reed.reedplayer.activity;

import com.reed.reedplayer.fragment.BaseFragment;
import com.reed.reedplayer.fragment.VideoPlayerFragment;

/**
 * Created by thinkreed on 16/7/22.
 */
public class VideoPlayerActivity extends FullScreenActivity {
  @Override
  protected BaseFragment getFragment() {
    return VideoPlayerFragment.newInstance(getIntent().getExtras());
  }
}
