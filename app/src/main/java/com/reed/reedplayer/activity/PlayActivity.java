package com.reed.reedplayer.activity;

import android.os.Bundle;

import com.reed.reedplayer.fragment.BaseFragment;
import com.reed.reedplayer.fragment.PlayerFragment;

/**
 * Created by thinkreed on 16/5/25.
 */
public class PlayActivity extends BaseActivity {
  @Override
  protected BaseFragment getFragment() {
    Bundle bundle = getIntent().getExtras();
    return PlayerFragment.getInstance(bundle);
  }
}
