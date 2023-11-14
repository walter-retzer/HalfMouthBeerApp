package app.halfmouth.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.halfmouth.theme.YellowContainerDark
import app.halfmouth.theme.YellowContainerLight
import app.halfmouth.theme.YellowPrimaryDark
import app.halfmouth.theme.YellowPrimaryLight
import app.halfmouth.theme.YellowSecondaryLight

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = YellowPrimaryDark,
            primaryVariant = YellowContainerLight,
            secondary = YellowSecondaryLight
        )
    } else {
        lightColors(
            primary = YellowPrimaryLight,
            primaryVariant = YellowContainerDark,
            secondary = YellowSecondaryLight
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )
    MaterialTheme(
        colors = colors,
        shapes = shapes,
        typography = typography,
        content = content
    )
}
