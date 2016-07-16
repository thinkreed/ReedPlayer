package com.reed.reedplayer.component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by thinkreed on 16/7/16.
 */
public abstract class BaseProxyHandler implements InvocationHandler {

  protected Object mOrigin;

  public BaseProxyHandler(Object origin) {
    this.mOrigin = origin;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return doProxy(proxy, method, args);
  }

  public abstract Object doProxy(Object proxy, Method method, Object[] args) throws Throwable;
}
