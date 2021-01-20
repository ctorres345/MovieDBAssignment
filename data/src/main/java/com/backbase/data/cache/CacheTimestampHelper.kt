package com.backbase.data.cache

import java.util.concurrent.TimeUnit

class CacheTimestampHelper {
    fun now(): Long = System.currentTimeMillis()
    fun moviePagesRefreshTime(): Long = TimeUnit.MINUTES.toMillis(30)
    fun appConfigRefreshTime(): Long = TimeUnit.DAYS.toMillis(7)
    fun movieDetailsRefreshTime(): Long = TimeUnit.MINUTES.toMillis(30)
}
