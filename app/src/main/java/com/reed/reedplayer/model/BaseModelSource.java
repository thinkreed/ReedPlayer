package com.reed.reedplayer.model;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by thinkreed on 16/5/23.
 */
public abstract class BaseModelSource {

  public interface SourceObserver {
    void onDataRetrived(List<Model> models);
  }

  private List<SourceObserver> mObservers = new ArrayList<>();

  public void retriveData() {
    Observable.create(new Observable.OnSubscribe<List<Model>>() {
      @Override
      public void call(Subscriber<? super List<Model>> subscriber) {
        subscriber.onNext(getModels());
        subscriber.onCompleted();
      }
    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<Model>>() {
          @Override
          public void onCompleted() {

      }

          @Override
          public void onError(Throwable e) {

      }

          @Override
          public void onNext(List<Model> models) {
            notifyDataRetrived(models);
          }
        });
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
