package app.halfmouth.android.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import app.halfmouth.android.R
import app.halfmouth.android.components.BottomBarMenu
import app.halfmouth.theme.OnBackgroundDark
import app.halfmouth.theme.OnSurfaceVariantDark
import app.halfmouth.theme.SurfaceDark
import app.halfmouth.theme.SurfaceVariantDark
import app.halfmouth.theme.YellowContainerLight

@Composable
fun NotificationScreen(navController: NavHostController) {

    BackHandler { }

    Scaffold(
        scaffoldState = rememberScaffoldState(),
        bottomBar = {
            BottomBarMenu(navController = navController)
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            LoadScreen()
        }
    }

}


@Composable
fun LoadScreen() {
    ConstraintLayout(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        val (text, rectangle, card) = createRefs()

        Text(
            text = "Notificações",
            style = TextStyle(
                color = YellowContainerLight,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            ),
            modifier = Modifier
                .padding(20.dp)
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(rectangle) {
                    top.linkTo(text.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            val size = size
            drawRoundRect(
                SurfaceVariantDark,
                topLeft = Offset(0f, 0f),
                size = size,
                cornerRadius = CornerRadius(30.dp.toPx(), 30.dp.toPx()),
            )
        }

        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 45.dp, bottom = 45.dp)
                .constrainAs(card) {
                    top.linkTo(rectangle.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            items(10) {
                Card(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                        .background(SurfaceVariantDark)
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = 4.dp,
                    backgroundColor = OnBackgroundDark
                ) {
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.perfil_default),
                            contentDescription = "",
                            modifier = Modifier
                                .size(100.dp)
                                .padding(top = 10.dp, start = 10.dp, bottom = 10.dp)
                                .clip(RoundedCornerShape(100))
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 110.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                                .wrapContentHeight(),
                            text = "05/12/2023 - Nova Cerveja Session IPA em Produção!! \nFaça logo a sua reserva, não perca!",
                            style = TextStyle(
                                color = SurfaceDark,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.Start,
                            ),
                        )

                    }
                }
            }
        }
    }
}
