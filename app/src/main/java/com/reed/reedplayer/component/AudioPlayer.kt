package com.reed.reedplayer.component

import android.media.MediaPlayer
import android.util.Log

import com.reed.reedplayer.ReedApplication
import com.reed.reedplayer.model.Model
import com.reed.reedplayer.utils.Consts

/**
 * Created by thinkreed on 16/5/25.
 */
class AudioPlayer(firstSong: Model) : MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private var mMediaPlayer: MediaPlayer? = null
    var isReady: Boolean = false
        private set
    private var mPlayingStateChangedListener: OnPlayingStateChangedListener? = null

    override fun onCompletion(mp: MediaPlayer) {
        next()
    }

    interface OnPlayingStateChangedListener {
        fun onPlayingStateChanged(state: Int)

        fun onSongChanged(newSong: Model)
    }

    init {
        mMediaPlayer = MediaPlayer()
        mMediaPlayer!!.setOnPreparedListener(this)
        mMediaPlayer!!.setOnCompletionListener(this)
        ReedApplication.getInstance().queueManager.setCurrentIndex(firstSong)
        reset(firstSong.song.path)
    }

    fun togglePlayingState() {
        if (isReady && mPlayingStateChangedListener != null) {
            if (mMediaPlayer!!.isPlaying) {
                mMediaPlayer!!.pause()
                mPlayingStateChangedListener!!.onPlayingStateChanged(STATE_PAUSED)
            } else {
                mMediaPlayer!!.start()
                mPlayingStateChangedListener!!.onPlayingStateChanged(STATE_PLAYING)
            }
        }
    }

    fun release() {
        mMediaPlayer!!.stop()
        mMediaPlayer!!.release()
        mMediaPlayer = null
        isReady = false
        mPlayingStateChangedListener = null
    }

    private fun reset(path: String) {
        try {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.reset()
            mMediaPlayer!!.setDataSource(path)
            mMediaPlayer!!.prepareAsync()
        } catch (e: Exception) {
            Log.e(Consts.TAG, e.message)
        }

    }

    operator fun next() {
        val nextSong = ReedApplication.getInstance().queueManager.next()
        if (nextSong != null) {
            reset(nextSong.song.path)
            if (mPlayingStateChangedListener != null) {
                mPlayingStateChangedListener!!.onSongChanged(nextSong)
            }
        }
    }

    fun previous() {
        val prevSong = ReedApplication.getInstance().queueManager.prev()
        if (prevSong != null) {
            reset(prevSong.song.path)
            if (mPlayingStateChangedListener != null) {
                mPlayingStateChangedListener!!.onSongChanged(prevSong)
            }
        }
    }

    fun setPlayingStateChangedListener(l: OnPlayingStateChangedListener) {
        mPlayingStateChangedListener = l
    }

    override fun onPrepared(mp: MediaPlayer) {
        isReady = true
        togglePlayingState()
    }

    val currentPosition: Int
        get() = if (mMediaPlayer != null) mMediaPlayer!!.currentPosition else 0

    fun seekTo(position: Int) {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.seekTo(position)
        }
    }

    companion object {

        val STATE_PLAYING = 0
        val STATE_PAUSED = 1
        val STATE_STOPED = 2
    }
}
