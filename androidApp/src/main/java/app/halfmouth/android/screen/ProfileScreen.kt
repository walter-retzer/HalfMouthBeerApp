package app.halfmouth.android.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import app.halfmouth.android.R
import app.halfmouth.android.components.BottomBarMenu
import app.halfmouth.android.utils.Constants.Companion.USER_NULL
import app.halfmouth.android.viewmodel.ProfileViewModel
import app.halfmouth.theme.OnSurfaceDark
import app.halfmouth.theme.OutlineDark
import app.halfmouth.theme.SurfaceVariantDark
import app.halfmouth.theme.YellowContainerLight
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProfileScreen(navController: NavHostController) {
    val viewModel = viewModel<ProfileViewModel>()
    val name by viewModel.name.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val phone by viewModel.phone.collectAsStateWithLifecycle()
    val image by viewModel.image.collectAsStateWithLifecycle()


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
            if (image == "null") {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.perfil_default),
                        contentDescription = name,
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
                        painter = rememberAsyncImagePainter(image),
                        contentDescription = name,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(120.dp)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(100))
                    )
                }
            }

            if (name == USER_NULL) {
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
            if (name.isNotEmpty() && name != USER_NULL) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = name,
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
                    text = email,
                    style = TextStyle(
                        color = OutlineDark,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            if (phone != USER_NULL) {
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
                    Text(
                        text = phone,
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
                        .padding(8.dp)
                        .clickable { viewModel.isClicked = true },
                    painter = painterResource(id = R.drawable.icon_logout),
                    contentDescription = null,
                    tint = OnSurfaceDark
                )
                Text(
                    modifier = Modifier.clickable { viewModel.isClicked = true },
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
            if (viewModel.isClicked) {
                DeleteUserAccountScreen(navController)
            }
        }
    }
}
