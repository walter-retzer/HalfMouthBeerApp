package app.halfmouth.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import app.halfmouth.android.R
import app.halfmouth.theme.OnSurfaceDark

@Composable
fun ContactTextField(
    value: String,
    placeholder: String,
    error: String?,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    inputType: KeyboardOptions,
    errorReset: () -> Unit,
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


@Composable
fun ContactTextFieldPassword(
    value: String,
    placeholder: String,
    error: String?,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    inputType: KeyboardOptions,
    errorReset: () -> Unit
) {
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

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
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    if (passwordVisibility) {
                        Icon(
                            modifier = Modifier.clickable { passwordVisibility = !passwordVisibility },
                            painter = painterResource(id = R.drawable.icon_eye_close),
                            contentDescription = null,
                            tint = OnSurfaceDark
                        )
                    } else {
                        Icon(
                            modifier = Modifier.clickable { passwordVisibility = !passwordVisibility },
                            painter = painterResource(id = R.drawable.icon_eye_open),
                            contentDescription = null,
                            tint = OnSurfaceDark
                        )
                    }
                }
            }
        )
        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
