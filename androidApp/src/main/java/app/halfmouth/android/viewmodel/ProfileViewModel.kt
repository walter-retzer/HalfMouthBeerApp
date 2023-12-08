package app.halfmouth.android.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.halfmouth.android.data.googleAuth.GoogleAuthUiClient
import app.halfmouth.android.data.googleAuth.SignInState
import app.halfmouth.android.main.AndroidApp
import app.halfmouth.android.security.SecurePreferencesApp
import app.halfmouth.android.utils.Constants.Companion.USER_CELLPHONE
import app.halfmouth.android.utils.Constants.Companion.USER_DEFAULT_SIGNIN
import app.halfmouth.android.utils.Constants.Companion.USER_EMAIL
import app.halfmouth.android.utils.Constants.Companion.USER_GOOGLE_SIGNIN
import app.halfmouth.android.utils.Constants.Companion.USER_IMAGE
import app.halfmouth.android.utils.Constants.Companion.USER_NAME
import app.halfmouth.android.utils.Constants.Companion.USER_NULL
import app.halfmouth.android.utils.Constants.Companion.USER_UID
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val pref = SecurePreferencesApp()
    private val signInGoogle = pref.get(USER_GOOGLE_SIGNIN) ?: false
    private val signInDefault = pref.get(USER_DEFAULT_SIGNIN) ?: false

    var isClicked by mutableStateOf(false)

    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    private val _email = MutableStateFlow(USER_NULL)
    val email = _email.asStateFlow()

    private val _phone = MutableStateFlow(USER_NULL)
    val phone = _phone.asStateFlow()

    private val _name = MutableStateFlow(USER_NULL)
    val name = _name.asStateFlow()

    private val _image = MutableStateFlow(USER_NULL)
    val image = _image.asStateFlow()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(oneTapClient = Identity.getSignInClient(AndroidApp.applicationContext))
    }

    init {
        if (signInGoogle) {
            googleAuthUiClient.getSignedInUser()
            getPref()
        }
        if (signInDefault) getPref()
    }

    private fun getPref() {
        _email.value = pref.get(USER_EMAIL) ?: USER_NULL
        _phone.value = pref.get(USER_CELLPHONE) ?: USER_NULL
        _name.value = pref.get(USER_NAME) ?: USER_NULL
        _image.value = pref.get(USER_IMAGE) ?: USER_NULL
    }

    fun deleteAccount() {
        _signInState.update { SignInState() }
        resetGoogleAccount()
        pref.delete(USER_UID)
        pref.delete(USER_GOOGLE_SIGNIN)
        pref.delete(USER_DEFAULT_SIGNIN)
        pref.delete(USER_NAME)
        pref.delete(USER_EMAIL)
        pref.delete(USER_CELLPHONE)
        pref.delete(USER_IMAGE)
    }

    private fun resetGoogleAccount() {
        viewModelScope.launch {
            googleAuthUiClient.signOut()
        }
    }
}
