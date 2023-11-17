package app.halfmouth.android.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val body: String,
    val title: String,
    val userId: Int,
)