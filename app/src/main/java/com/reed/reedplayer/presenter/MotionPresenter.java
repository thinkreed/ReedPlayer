package com.reed.reedplayer.presenter;

import android.content.Intent;
import android.view.View;

import com.reed.reedplayer.model.Model;
import com.reed.reedplayer.utils.Consts;

/**
 * Created by thinkreed on 16/5/24.
 */
public class MotionPresenter extends Presenter {
  @Override
  protected void bind(final Model model) {
    getView().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        switch (getId()) {
          case 0:
            Intent intent = new Intent(getView().getContext(), model.motion.clazz);
            intent.putExtra(Consts.KEY_MODEL, model);
            getView().getContext().startActivity(intent);
            break;
        }
      }
    });
  }
}
