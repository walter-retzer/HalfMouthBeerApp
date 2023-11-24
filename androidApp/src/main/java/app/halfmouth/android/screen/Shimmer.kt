package app.halfmouth.android.screen

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AnimatedShimmer(
    screen: ShimmerScreen
) {
    val shimmerCollorsLight = listOf(
        Color.Gray.copy(0.9f),
        Color.Gray.copy(0.5f),
        Color.Gray.copy(0.9f)
    )

    val shimmerCollorsDark = listOf(
        Color.White.copy(0.4f),
        Color.White.copy(0.2f),
        Color.White.copy(0.4f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1800f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(
                durationMillis = 1200,
                easing = FastOutLinearInEasing
            ),
            RepeatMode.Reverse
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerCollorsLight,
        start = Offset.Zero,
        end = Offset(translateAnim.value, translateAnim.value)
    )

    when (screen) {
        ShimmerScreen.HOME -> ShimmerHome(brush = brush)
        ShimmerScreen.PROFILE -> ShimmerProfile(brush = brush)
        ShimmerScreen.NOTIFICATIONS -> ShimmerNotifications(brush = brush)
    }
}

@Composable
fun ShimmerHome(brush: Brush) {
    repeat(6) {
        Spacer(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
                .height(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(brush)
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun ShimmerNotifications(brush: Brush) { }

@Composable
fun ShimmerProfile(brush: Brush) { }


enum class ShimmerScreen {
    HOME, PROFILE, NOTIFICATIONS
}
