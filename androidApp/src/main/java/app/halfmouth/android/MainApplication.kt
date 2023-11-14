package app.halfmouth.android

import android.app.Application
import android.content.Context
import app.halfmouth.utils.AndroidApp

class MainApplication: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        base?.let {
            AndroidApp.applicationContext = it
        }
    }
}