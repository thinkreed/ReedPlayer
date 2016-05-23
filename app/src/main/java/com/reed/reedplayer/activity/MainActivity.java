package com.reed.reedplayer.activity;

import android.os.Bundle;

import com.reed.reedplayer.fragment.BaseFragment;
import com.reed.reedplayer.fragment.MainFragment;

public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected BaseFragment getFragment() {
    return MainFragment.getInstance();
  }
}
