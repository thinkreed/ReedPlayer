package com.reed.reedplayer.activity;

import android.os.Bundle;

import com.reed.reedplayer.fragment.BaseFragment;
import com.reed.reedplayer.fragment.ItemListFragment;
import com.reed.reedplayer.utils.Consts;
import com.reed.reedplayer.utils.SourceType;

/**
 * Created by thinkreed on 16/5/23.
 */
public class SongListActivity extends BaseActivity {
  @Override
  protected BaseFragment getFragment() {
    Bundle bundle = new Bundle();
    bundle.putInt(Consts.KEY_TYPE, SourceType.LOCAL_MUSIC.ordinal());
    return ItemListFragment.getInstance(bundle);
  }
}
