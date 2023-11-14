package app.halfmouth.core

import androidx.compose.runtime.Composable

@Composable
expect fun DynamicColorsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)