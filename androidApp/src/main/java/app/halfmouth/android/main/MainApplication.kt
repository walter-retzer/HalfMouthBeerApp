package app.halfmouth.android.main

import android.app.Application
import android.content.Context

class MainApplication: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        base?.let {
            AndroidApp.applicationContext = it
        }
    }
}