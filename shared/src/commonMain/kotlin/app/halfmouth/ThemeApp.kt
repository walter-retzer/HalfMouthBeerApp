package app.halfmouth

import androidx.compose.runtime.Composable
import app.halfmouth.core.DynamicColorsTheme

@Composable
fun ThemeApp(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    DynamicColorsTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
    }
}
