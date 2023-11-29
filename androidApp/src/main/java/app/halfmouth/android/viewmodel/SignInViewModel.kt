package app.halfmouth.android.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import app.halfmouth.android.data.contact.Contact
import app.halfmouth.android.data.contact.ContactListEvent
import app.halfmouth.android.data.contact.ContactListState
import app.halfmouth.android.data.contact.ContactValidator
import app.halfmouth.android.data.googleAuth.SignInResult
import app.halfmouth.android.data.googleAuth.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel : ViewModel() {

    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    private val _state = MutableStateFlow(ContactListState())
    val state = _state.asStateFlow()

    var newContact: Contact? by mutableStateOf(
        Contact(
            firstName = "",
            lastName = "",
            email = "",
            phoneNumber = "",
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

    fun onEvent(event: ContactListEvent) {
        when (event) {
            is ContactListEvent.OnEmailChanged -> {
                newContact = newContact?.copy(
                    email = event.value
                )
            }

            is ContactListEvent.OnFirstNameChanged -> {
                newContact = newContact?.copy(
                    firstName = event.value
                )
            }

            is ContactListEvent.OnLastNameChanged -> {
                newContact = newContact?.copy(
                    lastName = event.value
                )
            }

            is ContactListEvent.OnPhoneNumberChanged -> {
                newContact = newContact?.copy(
                    phoneNumber = event.value
                )
            }

            is ContactListEvent.SaveContact -> {
                newContact?.let { contact ->
                    val result = ContactValidator.validateContact(contact)
                    val errors = listOfNotNull(
                        result.firstNameError,
                        result.lastNameError,
                        result.emailError,
                        result.phoneNumberError
                    )
                    if (errors.isEmpty()) {
                        _state.update {
                            it.copy(
                                firstNameError = null,
                                lastNameError = null,
                                emailError = null,
                                phoneNumberError = null
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(
                                firstNameError = result.firstNameError,
                                lastNameError = result.lastNameError,
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
                lastNameError = null,
                emailError = null,
                phoneNumberError = null
            )
        }
    }
}