package com.reed.reedplayer.component;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by thinkreed on 16/7/14.
 */
public class ProxyInstrumentation extends Instrumentation {

  private Instrumentation mOrigin;

  public ProxyInstrumentation(Instrumentation instrumentation) {
    this.mOrigin = instrumentation;
  }

  public ActivityResult execStartActivity(
      Context who, IBinder contextThread, IBinder token, Activity target,
      Intent intent, int requestCode, Bundle options) {
    Log.d("thinkreed", "this is my Instrumentation hooked!");
    try {
      Method execStartActivity =
          Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class, IBinder.class,
              IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
      execStartActivity.setAccessible(true);
      return (ActivityResult) execStartActivity.invoke(mOrigin, who, contextThread, token, target,
          intent, requestCode, options);
    } catch (Exception e) {
      return null;
    }
  }
}
