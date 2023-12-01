package app.halfmouth.android.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import app.halfmouth.android.R
import app.halfmouth.android.data.googleAuth.GoogleAuthUiClient
import app.halfmouth.android.security.SecurePreferencesApp
import app.halfmouth.android.utils.Constants
import app.halfmouth.theme.YellowContainerLight
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.auth.api.identity.Identity

@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val pref = SecurePreferencesApp()
    val userImage = pref.get(Constants.USER_IMAGE) ?: ""
    val userName = pref.get(Constants.USER_NAME) ?: ""

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(oneTapClient = Identity.getSignInClient(context))
    }

    ConstraintLayout(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        val (text, rectangle, image) = createRefs()

        Text(
            text = "Home",
            style = TextStyle(
                color = YellowContainerLight,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            ),
            modifier = Modifier
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )





        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp)
                .constrainAs(rectangle) {
                    top.linkTo(text.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            val size = size
            drawRoundRect(
                Color.White,
                topLeft = Offset(0f, -100f),
                size = size,
                cornerRadius = CornerRadius(30.dp.toPx(), 30.dp.toPx()),
            )
        }

        if (userImage.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(userImage),
                contentDescription = userName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .offset(25.dp, 70.dp)
                    .clip(RoundedCornerShape(35))
                    .constrainAs(image) {
                        top.linkTo(rectangle.top)
                        start.linkTo(parent.start)
                    }
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.profile_default_image),
                contentDescription = userName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .offset(25.dp, 70.dp)
                    .clip(RoundedCornerShape(35))
                    .constrainAs(image) {
                        top.linkTo(rectangle.top)
                        start.linkTo(parent.start)
                    }
            )
        }
    }
}

@Composable
fun UserContactPhoto() {


}