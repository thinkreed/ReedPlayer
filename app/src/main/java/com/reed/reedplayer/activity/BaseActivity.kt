package com.reed.reedplayer.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.reed.reedplayer.R

/**
 * Created by thinkreed on 16/5/23.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        val manager = supportFragmentManager
        var fragment: Fragment? = manager.findFragmentById(R.id.container)
        if (fragment == null) {
            fragment = getFragment()
            manager.beginTransaction().add(R.id.container, fragment).commit()
        }
    }

    protected abstract fun getFragment():Fragment
}
