package com.reed.reedplayer;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by thinkreed on 16/5/23.
 */
public class ReedApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    initializeFresco();
  }

  private void initializeFresco() {
    Fresco.initialize(this);
  }
}
