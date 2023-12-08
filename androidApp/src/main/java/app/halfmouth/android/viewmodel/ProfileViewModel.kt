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
import app.halfmouth.android.utils.Constants.Companion.USER_UID
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val pref = SecurePreferencesApp()
    private val signInGoogle = pref.get(USER_GOOGLE_SIGNIN) ?: false

    var isClicked by mutableStateOf(false)

    private val _signOutSuccessful = MutableStateFlow(false)
    val signOutSuccessful = _signOutSuccessful.asStateFlow()

    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(oneTapClient = Identity.getSignInClient(AndroidApp.applicationContext))
    }

    init {
        if (signInGoogle) googleAuthUiClient.getSignedInUser()
    }

    fun deleteAccount() {
        _signInState.update { SignInState() }
        deleteUserGoogleAccount()
        deleteUserFirebase()
        pref.delete(USER_UID)
        pref.delete(USER_GOOGLE_SIGNIN)
        pref.delete(USER_DEFAULT_SIGNIN)
        pref.delete(USER_NAME)
        pref.delete(USER_EMAIL)
        pref.delete(USER_CELLPHONE)
        pref.delete(USER_IMAGE)
    }

    private fun deleteUserGoogleAccount() {
        viewModelScope.launch {
            googleAuthUiClient.signOut()
            _signOutSuccessful.value = true
            delay(3000L)
        }
    }

    private fun deleteUserFirebase() = viewModelScope.launch {
        FirebaseAuth.getInstance().currentUser?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _signOutSuccessful.value = true
            }
        }
        delay(3000L)
    }
}
