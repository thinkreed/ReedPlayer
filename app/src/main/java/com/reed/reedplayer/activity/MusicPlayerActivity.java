package com.reed.reedplayer.activity;

import android.os.Bundle;

import com.reed.reedplayer.fragment.BaseFragment;
import com.reed.reedplayer.fragment.MusicPlayerFragment;

/**
 * Created by thinkreed on 16/5/25.
 */
public class MusicPlayerActivity extends FullScreenActivity {
  @Override
  protected BaseFragment getFragment() {
    Bundle bundle = getIntent().getExtras();
    return MusicPlayerFragment.getInstance(bundle);
  }
}
