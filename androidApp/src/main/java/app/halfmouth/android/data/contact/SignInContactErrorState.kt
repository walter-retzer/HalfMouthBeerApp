package app.halfmouth.android.data.contact

data class SignInContactErrorState(
    val firstNameError: String? = null,
    val phoneNumberError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
)
