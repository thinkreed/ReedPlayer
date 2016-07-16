package com.reed.reedplayer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by thinkreed on 16/7/16.
 */
public class TargetActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d("thinkreed", "this is target activity");
  }
}
