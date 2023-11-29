package app.halfmouth.android.data.contact

sealed interface SignInContactEvent {
    data class OnFirstNameChanged(val value: String): SignInContactEvent
    data class OnLastNameChanged(val value: String): SignInContactEvent
    data class OnEmailChanged(val value: String): SignInContactEvent
    data class OnPhoneNumberChanged(val value: String): SignInContactEvent
    object SaveContact: SignInContactEvent
}