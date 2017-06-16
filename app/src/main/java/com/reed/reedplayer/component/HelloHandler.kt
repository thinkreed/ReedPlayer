package com.reed.reedplayer.component

import android.util.Log

import java.lang.reflect.Method

/**
 * Created by thinkreed on 16/7/15.
 */
class HelloHandler(origin: Any) : BaseProxyHandler(origin) {

    @Throws(Throwable::class)
    override fun doProxy(proxy: Any, method: Method, args: Array<Any>): Any {
        if ("hello" == method.name) {
            Log.d("thinkreed", "this is hellohandler!")
        }
        return method.invoke(mOrigin, *args)
    }
}
