package com.reed.reedplayer.component;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.reed.reedplayer.utils.Consts;

import java.lang.reflect.Field;

/**
 * Created by thinkreed on 16/7/16.
 */
public class StartActivityCallBack implements Handler.Callback {

  private Handler mOrigin;

  public StartActivityCallBack(Handler origin) {
    this.mOrigin = origin;
  }

  @Override
  public boolean handleMessage(Message msg) {
    Object obj = msg.obj;
    switch (msg.what) {
      case 100:
        try {
          Field intent = obj.getClass().getDeclaredField("intent");
          intent.setAccessible(true);
          Intent originIntent = (Intent) intent.get(obj);
          intent.set(obj, originIntent.getParcelableExtra(Consts.INSTANCE.getEXTRA_ORIGIN_INTENT()));
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
    }
    mOrigin.handleMessage(msg);
    return true;
  }
}
