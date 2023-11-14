package app.halfmouth.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import app.halfmouth.theme.DarkColorScheme
import app.halfmouth.theme.LightColorScheme
import app.halfmouth.theme.TypographyDefault


@Composable
actual fun DynamicColorsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = TypographyDefault,
        content = content
    )
}