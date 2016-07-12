package com.reed.reedplayer.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.reed.reedplayer.R;
import com.reed.reedplayer.fragment.BaseFragment;

/**
 * Created by thinkreed on 16/5/23.
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fragment);
    FragmentManager manager = getSupportFragmentManager();
    Fragment fragment = manager.findFragmentById(R.id.container);
    if (fragment == null) {
      fragment = getFragment();
      manager.beginTransaction().add(R.id.container, fragment).commit();
    }
  }

  protected abstract BaseFragment getFragment();
}
