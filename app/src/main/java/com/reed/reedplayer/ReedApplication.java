package com.reed.reedplayer;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.igexin.sdk.PushManager;
import com.reed.reedplayer.component.QueueManager;

/**
 * Created by thinkreed on 16/5/23.
 */
public class ReedApplication extends Application {

    private static ReedApplication sInstance;
    private QueueManager mQueueManager;

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
        initializeQueueManager();
        initializeGetui();
    }

    private void initializeGetui() {
        PushManager.getInstance().initialize(this.getApplicationContext(), com.reed.reedplayer.DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
    }

    private void initializeFresco() {
        Fresco.initialize(this);
    }

    private void initializeQueueManager() {
        this.mQueueManager = new QueueManager();
    }

    public QueueManager getQueueManager() {
        return this.mQueueManager;
    }
}
