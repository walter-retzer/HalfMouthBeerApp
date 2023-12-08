package app.halfmouth.android.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.halfmouth.theme.YellowContainerLight

@Composable
fun ConfirmDialog(
    message: String,
    buttonMessage: String = "Sim",
    negativeButtonMessage: String = "Não",
    color: Color = Color.Black,
    onClick: (() -> Unit)? = null,
    onDismissClick: (() -> Unit)? = null,
    setShowDialog: ((Boolean) -> Unit)
) {
    AlertDialog(
        onDismissRequest = { setShowDialog(false) },
        title = { Text(text = "Confirmação") },
        text = { Text(text = message) },
        confirmButton = {
            Button(
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(backgroundColor = color),
                onClick = {
                    if (onClick != null) {
                        onClick()
                    }
                    setShowDialog(false)
                }
            ) {
                Text(text = buttonMessage, color = YellowContainerLight)
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier,
                colors = ButtonDefaults.buttonColors(backgroundColor = color),
                onClick = {
                    if (onDismissClick != null) {
                        onDismissClick()
                    }
                    setShowDialog(false)
                }
            ) {
                Text(text = negativeButtonMessage, color = YellowContainerLight)
            }
        }
    )
}