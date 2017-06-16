package com.reed.reedplayer.adapter

import android.net.Uri
import android.support.annotation.FloatRange
import android.support.annotation.IntRange
import android.view.View

import com.devbrackets.android.exomedia.core.EMListenerMux
import com.devbrackets.android.exomedia.core.api.VideoViewApi
import com.devbrackets.android.exomedia.core.builder.RenderBuilder
import com.devbrackets.android.exomedia.core.video.scale.ScaleType
import com.devbrackets.android.exomedia.ui.widget.EMVideoView
import com.google.android.exoplayer.MediaFormat

/**
 * Created by thinkreed on 16/7/23.
 */
class EMVideoView2VideoViewApiAdapter(private val emVideoView: EMVideoView) : VideoViewApi {

    override fun getHeight(): Int {
        return 0
    }

    override fun getWidth(): Int {
        return 0
    }

    override fun setVideoUri(uri: Uri?) {

    }

    override fun setVideoUri(uri: Uri?, renderBuilder: RenderBuilder?) {

    }

    override fun setVolume(@FloatRange(from = 0.0, to = 1.0) volume: Float): Boolean {
        return false
    }

    override fun seekTo(@IntRange(from = 0L) milliseconds: Int) {
        emVideoView.seekTo(milliseconds)
    }

    override fun isPlaying(): Boolean {
        return emVideoView.isPlaying
    }

    override fun start() {
        emVideoView.start()
    }

    override fun pause() {
        emVideoView.pause()
    }

    override fun stopPlayback() {
        emVideoView.stopPlayback()
    }

    override fun restart(): Boolean {
        return false
    }

    override fun suspend() {

    }

    override fun release() {
        emVideoView.release()
    }

    override fun getDuration(): Int {
        return emVideoView.duration
    }

    override fun getCurrentPosition(): Int {
        return emVideoView.currentPosition
    }

    override fun getBufferedPercent(): Int {
        return 0
    }

    override fun trackSelectionAvailable(): Boolean {
        return false
    }

    override fun setTrack(trackType: Int, trackIndex: Int) {

    }

    override fun getAvailableTracks(): Map<Int, List<MediaFormat>>? {
        return null
    }

    override fun setScaleType(scaleType: ScaleType) {

    }

    override fun getScaleType(): ScaleType? {
        return null
    }

    override fun setMeasureBasedOnAspectRatioEnabled(doNotMeasureBasedOnAspectRatio: Boolean) {

    }

    override fun setVideoRotation(@IntRange(from = 0L, to = 359L) rotation: Int, fromUser: Boolean) {

    }

    override fun setOnTouchListener(listener: View.OnTouchListener) {

    }

    override fun setListenerMux(listenerMux: EMListenerMux) {

    }

    override fun onVideoSizeChanged(width: Int, height: Int) {

    }
}
