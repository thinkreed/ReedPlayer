package com.reed.reedplayer.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.reed.reedplayer.presenter.ViewGroupPresenter;

/**
 * Created by thinkreed on 16/5/23.
 */
public class ReedPlayerAdapter extends BaseReedAdapter {
  @Override
  protected ViewGroupPresenter createViewGroupPresenter(ViewGroup parent, Context context,
      int viewType) {
    return null;
  }

  @Override
  public int getItemViewType(int position) {
    return getDataList().get(position).templete.ordinal();
  }
}
