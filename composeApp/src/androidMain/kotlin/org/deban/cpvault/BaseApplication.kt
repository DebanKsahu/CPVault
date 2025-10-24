package org.deban.cpvault

import android.app.Application
import org.deban.cpvault.di.initKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}