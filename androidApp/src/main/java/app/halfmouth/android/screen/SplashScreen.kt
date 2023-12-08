package app.halfmouth.android.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import app.halfmouth.android.security.SecurePreferencesApp
import app.halfmouth.android.utils.Constants.Companion.USER_DEFAULT_SIGNIN
import app.halfmouth.android.utils.Constants.Companion.USER_GOOGLE_SIGNIN
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val pref = SecurePreferencesApp()
    val userGoogle = pref.get(USER_GOOGLE_SIGNIN) ?: false
    val userDefault = pref.get(USER_DEFAULT_SIGNIN) ?: false

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        if (userGoogle || userDefault) navController.navigate(ScreenRoute.HomeScreen.route)
        else navController.navigate(ScreenRoute.SignInScreen.route)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = app.halfmouth.R.drawable.logohalfmouth),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}
