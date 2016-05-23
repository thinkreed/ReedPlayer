package com.reed.reedplayer.presenter;

import android.view.View;

import com.reed.reedplayer.model.Model;


/**
 * Created by thinkreed on 16/5/4.
 */
public abstract class Presenter {

  View view;
  int id;

  protected abstract void bind(Model model);

  public int getId() {
    return id;
  }

  public View getView() {
    return view;
  }
}
