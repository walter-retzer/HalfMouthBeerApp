package app.halfmouth.android.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.halfmouth.android.data.contact.SignInContact
import app.halfmouth.android.data.contact.SignInContactEvent
import app.halfmouth.android.data.contact.SignInContactErrorState
import app.halfmouth.android.data.contact.SignInContactValidator
import app.halfmouth.android.data.googleAuth.SignInResult
import app.halfmouth.android.data.googleAuth.SignInState
import app.halfmouth.android.security.SecurePreferencesApp
import app.halfmouth.android.utils.Constants.Companion.USER_CELLPHONE
import app.halfmouth.android.utils.Constants.Companion.USER_DEFAULT_SIGNIN
import app.halfmouth.android.utils.Constants.Companion.USER_EMAIL
import app.halfmouth.android.utils.Constants.Companion.USER_NAME
import app.halfmouth.android.utils.Constants.Companion.USER_UID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class SignInViewModel : ViewModel() {
    private val pref = SecurePreferencesApp()

    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    private val _state = MutableStateFlow(SignInContactErrorState())
    val state = _state.asStateFlow()

    private val _signInSuccessful = MutableStateFlow(false)
    val signInSuccessful = _signInSuccessful.asStateFlow()

    private val _signInError = MutableStateFlow(false)
    val signInError = _signInError.asStateFlow()

    var newContact: SignInContact by mutableStateOf(
        SignInContact(
            firstName = "",
            phoneNumber = "",
            email = "",
            password = "",
            confirmPassword = ""
        )
    )
        private set

    fun onSignInResult(result: SignInResult) {
        _signInState.update { state ->
            state.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _signInState.update { SignInState() }
    }

    fun onEvent(event: SignInContactEvent) {
        when (event) {
            is SignInContactEvent.OnFirstNameChanged -> {
                newContact = newContact.copy(
                    firstName = event.value
                )
                pref.put(USER_NAME, event.value)

            }

            is SignInContactEvent.OnPhoneNumberChanged -> {
                newContact = newContact.copy(
                    phoneNumber = event.value
                )
                pref.put(USER_CELLPHONE, event.value)
            }

            is SignInContactEvent.OnEmailChanged -> {
                newContact = newContact.copy(
                    email = event.value
                )
                pref.put(USER_EMAIL, event.value)
            }

            is SignInContactEvent.OnPasswordChanged -> {
                newContact = newContact.copy(
                    password = event.value
                )
            }

            is SignInContactEvent.OnConfirmPasswordChanged -> {
                newContact = newContact.copy(
                    confirmPassword = event.value
                )
            }

            is SignInContactEvent.SaveContact -> {
                newContact.let { contact ->
                    val result = SignInContactValidator.validateContact(contact)
                    val errors = listOfNotNull(
                        result.firstNameError,
                        result.passwordError,
                        result.confirmPasswordError,
                        result.emailError,
                        result.phoneNumberError
                    )
                    if (errors.isEmpty()) {
                        _state.update {
                            it.copy(
                                firstNameError = null,
                                passwordError = null,
                                confirmPasswordError = null,
                                emailError = null,
                                phoneNumberError = null
                            )
                        }
                        createUser()
                    } else {
                        _state.update {
                            it.copy(
                                firstNameError = result.firstNameError,
                                passwordError = result.passwordError,
                                confirmPasswordError = result.confirmPasswordError,
                                emailError = result.emailError,
                                phoneNumberError = result.phoneNumberError
                            )
                        }
                    }
                }
            }
        }
    }

    fun resetErrorInputField() {
        _state.update {
            it.copy(
                firstNameError = null,
                passwordError = null,
                confirmPasswordError = null,
                emailError = null,
                phoneNumberError = null
            )
        }
    }

    private fun createUser() {
        val password = newContact.password
        val email = newContact.email
        val name = newContact.firstName
        val cellphone = newContact.phoneNumber
        pref.put(USER_NAME, name)
        pref.put(USER_EMAIL, email)
        pref.put(USER_CELLPHONE, cellphone)
        pref.put(USER_DEFAULT_SIGNIN, true)
        _signInError.value = false

        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result.user
                    if (user != null) {
                        _signInSuccessful.value = true
                        Log.d("SignUpAuthFirebase", "Success! ${user.uid}")
                        user.sendEmailVerification()
                            .addOnCompleteListener { task ->
                                if (!task.isSuccessful) {
                                    _signInError.value = true
                                    Log.d("SignUpAuthFirebase", "Fail ${task.exception?.message}")
                                }
                            }
                        pref.put(USER_UID, user.uid)
                    } else {
                        _signInError.value = true
                        Log.d("SignUpAuthFirebase", "Empty or Null userId!")
                    }
                } else {
                    _signInError.value = true
                    val errorMessage: String = try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthUserCollisionException) {
                        "O endereço de e-mail já está sendo usado por outra conta."
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        "Endereço de e-mail mal formatado ou inválido."
                    }
                    Log.w("SignUpAuthFirebase", "Failure!", task.exception)
                }
            }
    }
}
