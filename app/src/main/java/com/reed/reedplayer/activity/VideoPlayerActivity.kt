package com.reed.reedplayer.activity

import com.reed.reedplayer.fragment.BaseFragment
import com.reed.reedplayer.fragment.VideoPlayerFragment

/**
 * Created by thinkreed on 16/7/22.
 */
class VideoPlayerActivity : FullScreenActivity() {
    override fun getFragment(): BaseFragment {
        return VideoPlayerFragment.newInstance(intent.extras)
    }
}
