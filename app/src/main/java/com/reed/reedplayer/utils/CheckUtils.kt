package com.reed.reedplayer.utils

/**
 * Created by thinkreed on 16/5/23.
 */
object CheckUtils {

    operator fun <T> get(value: T?, defaultValue: T): T {
        return value ?: defaultValue
    }

    fun isEmpty(collection: Collection<*>?): Boolean {
        return collection == null || collection.isEmpty()
    }
}
