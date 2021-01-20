package com.backbase.assignment

import android.app.Application
import com.backbase.data.SessionManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MovieApplication : Application() {
   @Inject lateinit var sessionManager: SessionManager

    override fun onCreate() {
        super.onCreate()
       sessionManager.saveToken(sessionManager.getDefaultApiToken())
    }
}