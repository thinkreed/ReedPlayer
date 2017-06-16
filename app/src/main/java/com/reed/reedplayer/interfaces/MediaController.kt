package com.reed.reedplayer.interfaces

/**
 * Created by thinkreed on 16/7/23.
 */
interface MediaController {
    fun togglePlay()
    fun seekTo(milliSeconds: Int)
    operator fun next()
    fun prev()
    fun stop()
}
