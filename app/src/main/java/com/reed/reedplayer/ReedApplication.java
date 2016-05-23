package com.reed.reedplayer;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by thinkreed on 16/5/23.
 */
public class ReedApplication extends Application {

  private static ReedApplication sInstance;

  public static ReedApplication getInstance() {
    return sInstance;
  }

  public ReedApplication() {
    sInstance = this;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    initializeFresco();
  }

  private void initializeFresco() {
    Fresco.initialize(this);
  }
}
