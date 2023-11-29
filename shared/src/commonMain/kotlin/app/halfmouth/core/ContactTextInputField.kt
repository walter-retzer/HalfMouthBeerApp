package app.halfmouth.core

import androidx.compose.runtime.Composable

@Composable
expect fun ContactTextInputField(
    value: String,
    placeholder: String,
    error: String?,
    onValueChanged: (String) -> Unit
)