package com.reed.reedplayer.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.reed.reedplayer.component.ActivityManagerHandler;
import com.reed.reedplayer.component.HelloHandler;
import com.reed.reedplayer.component.IForTest;
import com.reed.reedplayer.component.ProxyInstrumentation;
import com.reed.reedplayer.component.StartActivityCallBack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
        // firstHook();
        // firstDynamicProxy();
        firstAndroidManagerServiceHook();
        firstHandleHookedStartActivity();
        Intent intent = new Intent(this, TargetActivity.class);
        startActivity(intent);
    }

    public void firstHook() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method reCurrentActivityThreadMethod =
                    activityThreadClass.getDeclaredMethod("currentActivityThread");
            reCurrentActivityThreadMethod.setAccessible(true);
            Object activityThread = reCurrentActivityThreadMethod.invoke(null);
            Field field = activityThreadClass.getDeclaredField("mInstrumentation");
            field.setAccessible(true);
            Instrumentation mInstrumentation = (Instrumentation) field.get(activityThread);
            Instrumentation proxyInstrumentation = new ProxyInstrumentation(mInstrumentation);
            field.set(activityThread, proxyInstrumentation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void firstDynamicProxy() {
        IForTest iForTest = new IForTest() {
            @Override
            public void hello() {
                Log.d("thinkreed", "this is in firstDynamicProxy!");
            }
        };
        iForTest = (IForTest) Proxy.newProxyInstance(iForTest.getClass().getClassLoader(),
                iForTest.getClass().getInterfaces(), new HelloHandler(iForTest));
        iForTest.hello();
    }

    public void firstAndroidManagerServiceHook() {
        try {
            Class<?> activityManagerService = Class.forName("android.app.ActivityManagerNative");
            Field fieldGDefault = activityManagerService.getDeclaredField("gDefault");
            fieldGDefault.setAccessible(true);
            Object gDefault = fieldGDefault.get(null);
            Class<?> singleton = Class.forName("android.util.Singleton");
            Field fieldInstance = singleton.getDeclaredField("mInstance");
            fieldInstance.setAccessible(true);
            Object activityManager = fieldInstance.get(gDefault);
            Class<?> IActivityManager = Class.forName("android.app.IActivityManager");
            Object proxyActivityManager = Proxy.newProxyInstance(IActivityManager.getClassLoader(),
                    new Class[]{IActivityManager}, new ActivityManagerHandler(activityManager));
            fieldInstance.set(gDefault, proxyActivityManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void firstHandleHookedStartActivity() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method reCurrentActivityThreadMethod =
                    activityThreadClass.getDeclaredMethod("currentActivityThread");
            reCurrentActivityThreadMethod.setAccessible(true);
            Object activityThread = reCurrentActivityThreadMethod.invoke(null);
            Field field = activityThreadClass.getDeclaredField("mH");
            field.setAccessible(true);
            Handler mH = (Handler) field.get(activityThread);
            Field mCallback = Handler.class.getDeclaredField("mCallback");
            mCallback.setAccessible(true);
            mCallback.set(mH, new StartActivityCallBack(mH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
