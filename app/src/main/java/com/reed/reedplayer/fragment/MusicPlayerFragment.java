package com.reed.reedplayer.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.reed.reedplayer.R;
import com.reed.reedplayer.component.AudioPlayer;
import com.reed.reedplayer.model.Model;
import com.reed.reedplayer.utils.Consts;

/**
 * Created by thinkreed on 16/5/25.
 */
public class MusicPlayerFragment extends BaseFragment
    implements
      AudioPlayer.OnPlayingStateChangedListener,
      SeekBar.OnSeekBarChangeListener {

  private TextView mTitle;
  private TextView mArtist;
  private AudioPlayer mPlayer;
  private ImageView mBtnPrev;
  private ImageView mBtnPause;
  private ImageView mBtnNext;
  private SimpleDraweeView mCover;
  private SeekBar mProgress;
  private Handler mHandler;

  public static MusicPlayerFragment getInstance(Bundle bundle) {
    MusicPlayerFragment fragment = new MusicPlayerFragment();
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
    mTitle = (TextView) view.findViewById(R.id.title);
    mArtist = (TextView) view.findViewById(R.id.artist);
    mBtnNext = (ImageView) view.findViewById(R.id.btn_next);
    mBtnPause = (ImageView) view.findViewById(R.id.btn_play_pause);
    mBtnPrev = (ImageView) view.findViewById(R.id.btn_prev);
    mCover = (SimpleDraweeView) view.findViewById(R.id.cover);
    mProgress = (SeekBar) view.findViewById(R.id.progress);
    mHandler = new Handler();
    mProgress.setOnSeekBarChangeListener(this);
    mBtnPause.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPlayer.togglePlayingState();
      }
    });
    mBtnPrev.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPlayer.previous();
      }
    });
    mBtnNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        mPlayer.next();
      }
    });
    Bundle bundle = getArguments();
    Model model = bundle.getParcelable(Consts.KEY_MODEL);
    if (model != null) {
      refreshSongInfo(model);
      mPlayer = new AudioPlayer(model);
      mPlayer.setPlayingStateChangedListener(this);
    }
  }

  private void refreshSongInfo(Model songInfo) {
    mTitle.setText(songInfo.song.title);
    mArtist.setText(songInfo.song.artist);
    mCover.setImageURI(songInfo.song.cover);
    mProgress.setProgress(0);
    mProgress.setMax(Integer.parseInt(songInfo.song.duration) / 1000);
  }

  @Override
  public void onDestroyView() {
    mPlayer.release();
    super.onDestroyView();
  }

  @Override
  public void onPlayingStateChanged(int state) {
    switch (state) {
      case AudioPlayer.STATE_PAUSED:
        mBtnPause
            .setImageDrawable(getResources().getDrawable(R.drawable.uamp_ic_play_arrow_white_24dp));
        mHandler.removeCallbacksAndMessages(null);
        break;
      case AudioPlayer.STATE_PLAYING:
        mBtnPause.setImageDrawable(getResources().getDrawable(R.drawable.uamp_ic_pause_white_24dp));
        mHandler.post(new RefreshTask());
        break;
      default:
        break;
    }
  }

  @Override
  public void onSongChanged(Model newSong) {
    refreshSongInfo(newSong);
  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    if (mPlayer != null && fromUser) {
      mPlayer.seekTo(progress * 1000);
    }
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {

  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {

  }

  class RefreshTask implements Runnable {
    @Override
    public void run() {
      if (mPlayer != null) {
        mProgress.setProgress(mPlayer.getCurrentPosition() / 1000);
        mHandler.postDelayed(this, 1000);
      }
    }
  }
}
