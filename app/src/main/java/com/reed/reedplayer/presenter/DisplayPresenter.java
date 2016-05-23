package com.reed.reedplayer.presenter;


import android.widget.TextView;

import com.reed.reedplayer.R;
import com.reed.reedplayer.model.Model;

/**
 * Created by thinkreed on 16/5/4.
 */
public class DisplayPresenter extends Presenter {
  @Override
  protected void bind(Model model) {
    Object content = getContent(model);
    if (content == null) {
      // TODO: 16/5/23
    }
    if (content instanceof String && getView() instanceof TextView) {
      ((TextView) getView()).setText((String) content);
    }
  }

  private Object getContent(Model model) {
    switch (getId()) {
      case R.id.artist:
        return model.artist;
      case R.id.title:
        return model.title;
      default:
        return null;
    }
  }
}
