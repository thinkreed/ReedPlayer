package com.reed.reedplayer.activity;

import android.os.Bundle;
import android.view.View;

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

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    if (hasFocus) {
      getWindow().getDecorView().setSystemUiVisibility(
          View.SYSTEM_UI_FLAG_LAYOUT_STABLE
              | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
              | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
              | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
              | View.SYSTEM_UI_FLAG_FULLSCREEN
              | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
  }
}
