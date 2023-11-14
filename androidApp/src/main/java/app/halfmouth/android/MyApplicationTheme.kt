package app.halfmouth.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import app.halfmouth.theme.DarkColorScheme
import app.halfmouth.theme.LightColorScheme
import app.halfmouth.theme.TypographyDefault

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if(darkTheme) DarkColorScheme else LightColorScheme
    val typography = TypographyDefault
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )
    MaterialTheme(
        colorScheme = colors,
        shapes = shapes,
        typography = typography,
        content = content
    )
}
