package com.reed.reedplayer.adapter;

import android.net.Uri;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.devbrackets.android.exomedia.core.EMListenerMux;
import com.devbrackets.android.exomedia.core.api.VideoViewApi;
import com.devbrackets.android.exomedia.core.builder.RenderBuilder;
import com.devbrackets.android.exomedia.core.video.scale.ScaleType;
import com.devbrackets.android.exomedia.ui.widget.EMVideoView;
import com.google.android.exoplayer.MediaFormat;

import java.util.List;
import java.util.Map;

/**
 * Created by thinkreed on 16/7/23.
 */
public class EMVideoView2VideoViewApiAdapter implements VideoViewApi {

  private EMVideoView emVideoView;

  public EMVideoView2VideoViewApiAdapter(EMVideoView emVideoView) {
    this.emVideoView = emVideoView;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public void setVideoUri(@Nullable Uri uri) {

  }

  @Override
  public void setVideoUri(@Nullable Uri uri, @Nullable RenderBuilder renderBuilder) {

  }

  @Override
  public boolean setVolume(@FloatRange(from = 0.0, to = 1.0) float volume) {
    return false;
  }

  @Override
  public void seekTo(@IntRange(from = 0L) int milliseconds) {
    emVideoView.seekTo(milliseconds);
  }

  @Override
  public boolean isPlaying() {
    return emVideoView.isPlaying();
  }

  @Override
  public void start() {
    emVideoView.start();
  }

  @Override
  public void pause() {
    emVideoView.pause();
  }

  @Override
  public void stopPlayback() {
    emVideoView.stopPlayback();
  }

  @Override
  public boolean restart() {
    return false;
  }

  @Override
  public void suspend() {

  }

  @Override
  public void release() {
    emVideoView.release();
  }

  @Override
  public int getDuration() {
    return emVideoView.getDuration();
  }

  @Override
  public int getCurrentPosition() {
    return emVideoView.getCurrentPosition();
  }

  @Override
  public int getBufferedPercent() {
    return 0;
  }

  @Override
  public boolean trackSelectionAvailable() {
    return false;
  }

  @Override
  public void setTrack(int trackType, int trackIndex) {

  }

  @Nullable
  @Override
  public Map<Integer, List<MediaFormat>> getAvailableTracks() {
    return null;
  }

  @Override
  public void setScaleType(@NonNull ScaleType scaleType) {

  }

  @Override
  public ScaleType getScaleType() {
    return null;
  }

  @Override
  public void setMeasureBasedOnAspectRatioEnabled(boolean doNotMeasureBasedOnAspectRatio) {

  }

  @Override
  public void setVideoRotation(@IntRange(from = 0L, to = 359L) int rotation, boolean fromUser) {

  }

  @Override
  public void setOnTouchListener(View.OnTouchListener listener) {

  }

  @Override
  public void setListenerMux(EMListenerMux listenerMux) {

  }

  @Override
  public void onVideoSizeChanged(int width, int height) {

  }
}
