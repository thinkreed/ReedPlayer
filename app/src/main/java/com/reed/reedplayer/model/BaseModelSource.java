package com.reed.reedplayer.model;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkreed on 16/5/23.
 */
public abstract class BaseModelSource {

  public interface SourceObserver {
    void onDataRetrived(List<Model> models);
  }

  private List<SourceObserver> mObservers = new ArrayList<>();
  private Handler mHandler = new Handler(Looper.getMainLooper());

  public void retriveData() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        final List<Model> models = getModels();
        if (models != null && models.size() > 0) {
          mHandler.post(new Runnable() {
            @Override
            public void run() {
              notifyDataRetrived(models);
            }
          });
        }
      }
    }).start();
  }

  public void registerObserver(SourceObserver observer) {
    mObservers.add(observer);
  }

  public void removeObserver(SourceObserver observer) {
    mObservers.remove(observer);
  }

  private void notifyDataRetrived(List<Model> models) {
    for (SourceObserver observer : mObservers) {
      observer.onDataRetrived(models);
    }
  }

  protected abstract List<Model> getModels();

}
