package com.reed.reedplayer.component;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by thinkreed on 16/7/15.
 */
public class ActivityManagerHandler implements InvocationHandler {

  private Object mOrigin;

  public ActivityManagerHandler(Object origin) {
    this.mOrigin = origin;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    Log.d("thinkreed", "this is ActivityManagerHandler, hooked by reed");
    return method.invoke(mOrigin, args);
  }
}
