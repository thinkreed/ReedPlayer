package com.reed.reedplayer.fragment

import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView

import com.facebook.drawee.view.SimpleDraweeView
import com.reed.reedplayer.R
import com.reed.reedplayer.component.AudioPlayer
import com.reed.reedplayer.model.Model
import com.reed.reedplayer.utils.Consts

/**
 * Created by thinkreed on 16/5/25.
 */
class MusicPlayerFragment : BaseFragment(), AudioPlayer.OnPlayingStateChangedListener, SeekBar.OnSeekBarChangeListener {

    private var mTitle: TextView? = null
    private var mArtist: TextView? = null
    private var mPlayer: AudioPlayer? = null
    private var mBtnPause: ImageView? = null
    private var mCover: SimpleDraweeView? = null
    private var mProgress: SeekBar? = null
    private var mHandler: Handler? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTitle = view!!.findViewById(R.id.title) as TextView
        mArtist = view.findViewById(R.id.artist) as TextView
        val btnNext = view.findViewById(R.id.btn_next)
        mBtnPause = view.findViewById(R.id.btn_play_pause) as ImageView
        val btnPrev = view.findViewById(R.id.btn_prev)
        mCover = view.findViewById(R.id.cover) as SimpleDraweeView
        mProgress = view.findViewById(R.id.progress) as SeekBar
        mHandler = Handler()
        mProgress!!.setOnSeekBarChangeListener(this)
        mBtnPause!!.setOnClickListener { mPlayer!!.togglePlayingState() }
        btnPrev.setOnClickListener { mPlayer!!.previous() }
        btnNext.setOnClickListener { mPlayer!!.next() }
        val bundle = arguments
        val model = bundle.getParcelable<Model>(Consts.KEY_MODEL)
        if (model != null) {
            refreshSongInfo(model)
            mPlayer = AudioPlayer(model)
            mPlayer!!.setPlayingStateChangedListener(this)
        }
    }

    private fun refreshSongInfo(songInfo: Model) {
        mTitle!!.text = songInfo.song.title
        mArtist!!.text = songInfo.song.artist
        mCover!!.setImageURI(songInfo.song.cover)
        mProgress!!.progress = 0
        mProgress!!.max = Integer.parseInt(songInfo.song.duration) / 1000
    }

    override fun onDestroyView() {
        mPlayer!!.release()
        super.onDestroyView()
    }

    override fun onPlayingStateChanged(state: Int) {
        when (state) {
            AudioPlayer.STATE_PAUSED -> {
                mBtnPause!!
                        .setImageDrawable(
                                ContextCompat.getDrawable(activity, R.drawable.uamp_ic_play_arrow_white_24dp))
                mHandler!!.removeCallbacksAndMessages(null)
            }
            AudioPlayer.STATE_PLAYING -> {
                mBtnPause!!.setImageDrawable(
                        ContextCompat.getDrawable(activity, R.drawable.uamp_ic_pause_white_24dp))
                mHandler!!.post(RefreshTask())
            }
            else -> {
            }
        }
    }

    override fun onSongChanged(newSong: Model) {
        refreshSongInfo(newSong)
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (mPlayer != null && fromUser) {
            mPlayer!!.seekTo(progress * 1000)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }

    internal inner class RefreshTask : Runnable {
        override fun run() {
            if (mPlayer != null) {
                mProgress!!.progress = mPlayer!!.currentPosition / 1000
                mHandler!!.postDelayed(this, 1000)
            }
        }
    }

    companion object {

        fun getInstance(bundle: Bundle): MusicPlayerFragment {
            val fragment = MusicPlayerFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
