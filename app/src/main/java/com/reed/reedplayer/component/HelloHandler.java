package com.reed.reedplayer.component;

import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by thinkreed on 16/7/15.
 */
public class HelloHandler extends BaseProxyHandler {

  public HelloHandler(Object origin) {
    super(origin);
  }

  @Override
  public Object doProxy(Object proxy, Method method, Object[] args) throws Throwable{
    if ("hello".equals(method.getName())) {
      Log.d("thinkreed", "this is hellohandler!");
    }
    return method.invoke(mOrigin, args);
  }
}
