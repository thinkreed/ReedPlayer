package com.reed.reedplayer.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.reed.reedplayer.R;
import com.reed.reedplayer.model.Model;
import com.reed.reedplayer.presenter.DisplayPresenter;
import com.reed.reedplayer.presenter.ViewGroupPresenter;

/**
 * Created by thinkreed on 16/5/23.
 */
public class SongListAdapter extends BaseReedAdapter {
  @Override
  protected ViewGroupPresenter createViewGroupPresenter(ViewGroup parent, Context context,
      int viewType) {
    switch (Model.Templete.values()[viewType]) {
      case ITEM_SONG:
        return new ViewGroupPresenter(parent, context, R.layout.item_song_info)
            .add(new DisplayPresenter(), R.id.title)
            .add(new DisplayPresenter(), R.id.cover)
            .add(new DisplayPresenter(), R.id.artist);
      default:
        return null;
    }
  }

  @Override
  public int getItemViewType(int position) {
    return getDataList().get(position).templete.ordinal();
  }
}
