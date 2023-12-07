package app.halfmouth.android.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import app.halfmouth.android.R
import app.halfmouth.android.components.BottomBarMenu
import app.halfmouth.android.data.googleAuth.GoogleAuthUiClient
import app.halfmouth.android.security.SecurePreferencesApp
import app.halfmouth.android.utils.Constants
import app.halfmouth.android.utils.Constants.Companion.USER_CELLPHONE
import app.halfmouth.android.utils.Constants.Companion.USER_EMAIL
import app.halfmouth.android.utils.Constants.Companion.USER_IMAGE
import app.halfmouth.android.utils.Constants.Companion.USER_NAME
import app.halfmouth.android.utils.Constants.Companion.USER_NULL
import app.halfmouth.android.utils.Constants.Companion.USER_PHONE_NULL
import app.halfmouth.theme.OnSurfaceDark
import app.halfmouth.theme.OutlineDark
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

    val init = googleAuthUiClient.getSignedInUser() != null
    val userGoogle = pref.get(Constants.USER_GOOGLE_SIGNIN) ?: false
    val userSignInDefault = pref.get(Constants.USER_DEFAULT_SIGNIN) ?: false
    val userImage = pref.get(USER_IMAGE) ?: ""
    val userName = pref.get(USER_NAME) ?: ""
    val userEmail = pref.get(USER_EMAIL) ?: ""
    val userCellphone = pref.get(USER_CELLPHONE) ?: ""

    BackHandler { }

    Scaffold(
        modifier = Modifier.background(SurfaceVariantDark),
        scaffoldState = rememberScaffoldState(),
        bottomBar = {
            BottomBarMenu(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color.Black),
        ) {

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 60.dp)

                ) {
                    val size = size
                    drawRoundRect(
                        SurfaceVariantDark,
                        topLeft = Offset(0f, 100f),
                        size = size,
                        cornerRadius = CornerRadius(30.dp.toPx(), 30.dp.toPx()),
                    )

                }
            }
        }

        Column(modifier = Modifier) {
            if(userImage == "null"){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.perfil_default),
                        contentDescription = userName,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(120.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(100))
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(userImage),
                        contentDescription = userName,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(120.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(100))
                    )
                }
            }

            if(userName == USER_NULL){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "USER",
                        style = TextStyle(
                            color = YellowContainerLight,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center
                        ),
                    )
                }
            }
            if(userName.isNotEmpty() && userName != USER_NULL){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = userName,
                        style = TextStyle(
                            color = YellowContainerLight,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center
                        ),
                    )
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.icon_email),
                    contentDescription = null,
                    tint = OnSurfaceDark
                )
                Text(
                    text = userEmail,
                    style = TextStyle(
                        color = OutlineDark,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center
                    ),
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.icon_phone),
                    contentDescription = null,
                    tint = OnSurfaceDark
                )
                if(userCellphone == Constants.USER_NULL){
                    Text(
                        text = USER_PHONE_NULL,
                        style = TextStyle(
                            color = OutlineDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center
                        ),
                    )
                }
                if(userCellphone.isNotEmpty()){
                    Text(
                        text = userCellphone,
                        style = TextStyle(
                            color = OutlineDark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center
                        ),
                    )
                }

            }

            Divider(
                color = OutlineDark,
                thickness = 1.dp,
                modifier = Modifier
                    .padding(12.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.icon_settings),
                    contentDescription = null,
                    tint = OnSurfaceDark
                )
                Text(
                    text = "Configurações",
                    style = TextStyle(
                        color = OutlineDark,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center
                    ),
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp),
                    painter = painterResource(id = R.drawable.icon_logout),
                    contentDescription = null,
                    tint = OnSurfaceDark
                )
                Text(
                    text = "Sair",
                    style = TextStyle(
                        color = OutlineDark,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center
                    ),
                )
            }

        }


    }
}
