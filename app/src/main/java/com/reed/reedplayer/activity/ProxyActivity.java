package com.reed.reedplayer.activity;

import android.app.Activity;
import android.os.Bundle;

import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by thinkreed on 16/7/13.
 */
public class ProxyActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    DexClassLoader dexClassLoader = new DexClassLoader("/storage/emulated/0/acg/app-debug.apk",
        getDir("dex", MODE_PRIVATE).getAbsolutePath(), null,
        ClassLoader.getSystemClassLoader().getParent());
    Class calledClass = null;
    try {
      calledClass = dexClassLoader.loadClass("com.trails.chatroomdemo.activity.TestUseActivity");
      Method method = calledClass.getDeclaredMethod("hello");
      method.setAccessible(true);
      method.invoke(calledClass.newInstance());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
