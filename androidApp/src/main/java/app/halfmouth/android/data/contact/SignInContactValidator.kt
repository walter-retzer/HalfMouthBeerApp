package app.halfmouth.android.data.contact

object SignInContactValidator {

    fun validateContact(contact: SignInContact): ValidationResult {
        var result = ValidationResult()

        if(contact.firstName.isBlank()) {
            result = result.copy(firstNameError = "O nome não foi preenchido.")
        }

        if(contact.phoneNumber.isBlank()) {
            result = result.copy(phoneNumberError = "O celular não foi preenchido.")
        }

        val emailRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
        if(!emailRegex.matches(contact.email)) {
            result = result.copy(emailError = "Digite um email válido")
        }

        if(contact.password.isBlank()) {
            result = result.copy(passwordError = "A senha esta vazia.")
        }

        if(contact.confirmPassword.isBlank()) {
            result = result.copy(passwordError = "A confirma senha esta vazia.")
        }

        if(contact.password.length <= 5) {
            result = result.copy(passwordError = "A senha deve conter 6 dígitos.")
        }

        if(contact.confirmPassword.length <= 5) {
            result = result.copy(confirmPasswordError = "A confirma senha deve conter 6 dígitos.")
        }

        if(contact.password != contact.confirmPassword) {
            result = result.copy(confirmPasswordError = "Verifique as senhas digitadas.")
        }

        return result
    }

    data class ValidationResult(
        val firstNameError: String? = null,
        val phoneNumberError: String? = null,
        val emailError: String? = null,
        val passwordError: String? = null,
        val confirmPasswordError: String? = null,
    )
}