package com.reed.reedplayer.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log

/**
 * Created by thinkreed on 16/7/16.
 */
class TargetActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("thinkreed", "this is target activity")
    }
}
