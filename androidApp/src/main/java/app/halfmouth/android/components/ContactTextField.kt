package app.halfmouth.android.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp

@Composable
fun ContactTextField(
    value: String,
    placeholder: String,
    error: String?,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    inputType: KeyboardOptions,
    errorReset: () -> Unit
) {
    Column(modifier) {
        OutlinedTextField(
            value = value,
            placeholder = { Text(text = placeholder) },
            onValueChange = onValueChanged,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { errorReset() },
            keyboardOptions = inputType,
        )
        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
