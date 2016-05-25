package com.reed.reedplayer.fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.reed.reedplayer.R;
import com.reed.reedplayer.model.Model;
import com.reed.reedplayer.utils.Consts;

/**
 * Created by thinkreed on 16/5/25.
 */
public class PlayerFragment extends BaseFragment {

  private TextView mTitle;
  private TextView mArtist;
  private MediaPlayer mMediaPlayer;

  public static PlayerFragment getInstance(Bundle bundle) {
    PlayerFragment fragment = new PlayerFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_player, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Bundle bundle = getArguments();
    Model model = bundle.getParcelable(Consts.KEY_MODEL);
    mTitle = (TextView) view.findViewById(R.id.title);
    mArtist = (TextView) view.findViewById(R.id.artist);
    mTitle.setText(model.title);
    mArtist.setText(model.artist);
    mMediaPlayer = new MediaPlayer();
    try {
      mMediaPlayer.setDataSource(model.path);
      mMediaPlayer.prepare();
      mMediaPlayer.start();
    } catch (Exception e) {
      Log.e(Consts.TAG, e.getMessage());
    }
  }

  @Override
  public void onDestroyView() {
    mMediaPlayer.stop();
    mMediaPlayer.release();
    mMediaPlayer = null;
    super.onDestroyView();
  }
}
