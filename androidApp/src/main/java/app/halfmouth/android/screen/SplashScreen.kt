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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import app.halfmouth.android.data.googleAuth.GoogleAuthUiClient
import app.halfmouth.android.security.SecurePreferencesApp
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    val pref = SecurePreferencesApp()
    val userId = pref.get("UUID") ?: ""

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(oneTapClient = Identity.getSignInClient(context))
    }

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
        if (googleAuthUiClient.getSignedInUser() != null || userId.isNotEmpty()) navController.navigate(ScreenRoute.HomeScreen.route)
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
