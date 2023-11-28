package app.halfmouth.android.data.googleAuth


data class SignInResult(
    val data: UserId?,
    val errorMessage: String? = null
)

data class UserId(
    val userId: String,
    val userName:String,
    val profilePictureUrl: String?
)
