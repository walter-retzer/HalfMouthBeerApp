package app.halfmouth.android.utils

import android.app.Activity
import android.view.WindowManager

fun Activity.lockScreen(lock: Boolean) = with(window) {
    if (lock) {
        setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        setFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        )
    } else {
        clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
    }
}