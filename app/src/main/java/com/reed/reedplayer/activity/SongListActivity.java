package com.reed.reedplayer.activity;

import com.reed.reedplayer.fragment.BaseFragment;
import com.reed.reedplayer.fragment.SongListFragment;

/**
 * Created by thinkreed on 16/5/23.
 */
public class SongListActivity extends BaseActivity {
  @Override
  protected BaseFragment getFragment() {
    return SongListFragment.getInstance();
  }
}
