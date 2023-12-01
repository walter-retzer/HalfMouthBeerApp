package app.halfmouth.android.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
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
import app.halfmouth.android.components.BottomBarMenu
import app.halfmouth.android.data.googleAuth.GoogleAuthUiClient
import app.halfmouth.android.security.SecurePreferencesApp
import app.halfmouth.android.utils.Constants
import app.halfmouth.theme.SurfaceVariantDark
import app.halfmouth.theme.YellowContainerLight
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.auth.api.identity.Identity

@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val pref = SecurePreferencesApp()
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(oneTapClient = Identity.getSignInClient(context))
    }


    BackHandler { }

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        bottomBar = {
            BottomBarMenu(navController = navController)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            val init = googleAuthUiClient.getSignedInUser() != null

            val userGoogle = pref.get(Constants.USER_GOOGLE_SIGNIN) ?: false
            val userSignInDefault = pref.get(Constants.USER_DEFAULT_SIGNIN) ?: false
            val userImage = pref.get(Constants.USER_IMAGE) ?: ""
            val userName = pref.get(Constants.USER_NAME) ?: ""
            val userEmail = pref.get(Constants.USER_EMAIL) ?: ""

            ConstraintLayout(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()
            ) {
                val (text, rectangle, image, image1, name, email) = createRefs()

                Text(
                    text = "Perfil",
                    style = TextStyle(
                        color = YellowContainerLight,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    ),
                    modifier = Modifier
                        .padding(top = 25.dp)
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
                        SurfaceVariantDark,
                        topLeft = Offset(0f, -100f),
                        size = size,
                        cornerRadius = CornerRadius(30.dp.toPx(), 30.dp.toPx()),
                    )
                }

                if (userGoogle && init) {
                    val imageUrl = userImage.ifEmpty { Constants.USER_DEFAULT_IMAGE }
                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
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

                if (userSignInDefault) {
                    Image(
                        painter = painterResource(id = R.drawable.perfil_default),
                        contentDescription = userName,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .offset(25.dp, 70.dp)
                            .clip(RoundedCornerShape(35))
                            .constrainAs(image1) {
                                top.linkTo(rectangle.top)
                                start.linkTo(parent.start)
                            }
                    )
                }

                Text(
                    text = userName,
                    style = TextStyle(
                        color = YellowContainerLight,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    ),
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .constrainAs(name) {
                            top.linkTo(image.bottom)
                            start.linkTo(parent.start)
                        }
                )

                Text(
                    text = userEmail,
                    style = TextStyle(
                        color = YellowContainerLight,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    ),
                    modifier = Modifier
                        .padding(top = 25.dp)
                        .constrainAs(email) {
                            top.linkTo(name.bottom)
                            start.linkTo(parent.start)
                        }
                )
            }
        }
    }
}
