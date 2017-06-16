package com.reed.reedplayer.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.reed.reedplayer.R;
import com.reed.reedplayer.adapter.EMVideoView2VideoViewApiAdapter;
import com.reed.reedplayer.component.ReedMediaController;
import com.reed.reedplayer.utils.Consts;

/**
 * Created by thinkreed on 16/7/22.
 */
public class VideoPlayerFragment extends BaseFragment {

  private ReedMediaController mMediaController;

  public static VideoPlayerFragment newInstance(Bundle bundle) {
    VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();
    videoPlayerFragment.setArguments(bundle);
    return videoPlayerFragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_video_player, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    SeekBar seekBar = (SeekBar) view.findViewById(R.id.progress);
    ImageView btnPlayPause = (ImageView) view.findViewById(R.id.btn_play_pause);
    EMVideoView emVideoView = (EMVideoView) view.findViewById(R.id.video_view);
    Handler handler = new Handler();
    mMediaController = ReedMediaController.newBuilder()
        .btnPlayPause(btnPlayPause)
        .videoViewApiImpl(new EMVideoView2VideoViewApiAdapter(emVideoView))
        .progressHandler(handler)
        .seekBar(seekBar)
        .build();
    emVideoView.setOnPreparedListener(new OnPreparedListener() {
      @Override
      public void onPrepared() {
        mMediaController.start();
      }
    });
    String path = getArguments().getString(Consts.INSTANCE.getKEY_PATH());
    emVideoView.setVideoPath(path);
  }


  @Override
  public void onDestroyView() {
    mMediaController.stop();
    super.onDestroyView();
  }
}
