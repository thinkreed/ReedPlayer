package com.reed.reedplayer.component;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by thinkreed on 16/7/15.
 */
public class HelloHandler implements InvocationHandler {

  private IForTest mOrigin;

  public HelloHandler(IForTest origin) {
    this.mOrigin = origin;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if ("hello".equals(method.getName())) {
      Log.d("thinkreed", "this is hellohandler!");
      method.invoke(mOrigin);
    }
    return null;
  }
}
