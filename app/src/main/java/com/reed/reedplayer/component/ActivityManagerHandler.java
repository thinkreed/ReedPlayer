package com.reed.reedplayer.component;

import android.content.ComponentName;
import android.content.Intent;

import com.reed.reedplayer.activity.ForTestActivity;
import com.reed.reedplayer.utils.Consts;

import java.lang.reflect.Method;

/**
 * Created by thinkreed on 16/7/15.
 */
public class ActivityManagerHandler extends BaseProxyHandler {

  public ActivityManagerHandler(Object origin) {
    super(origin);
  }

  @Override
  public Object doProxy(Object proxy, Method method, Object[] args) throws Throwable {
    if ("startActivity".equals(method.getName())) {
      int index = 0;
      int count = args.length;
      for (int i = 0; i < count; i++) {
        if (args[i] instanceof Intent) {
          index = i;
          break;
        }
      }
      Intent intent = new Intent();
      ComponentName componentName =
          new ComponentName("com.reed.reedplayer", ForTestActivity.class.getCanonicalName());
      intent.setComponent(componentName);
      intent.putExtra(Consts.INSTANCE.getEXTRA_ORIGIN_INTENT(), (Intent) args[index]);
      args[index] = intent;
    }
    return method.invoke(mOrigin, args);
  }
}
