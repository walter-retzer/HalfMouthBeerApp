package app.halfmouth.android.screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import app.halfmouth.theme.YellowContainerLight

@Composable
fun HomeScreen(navController: NavController) {
    MyApplicationTheme {
        LoadScreen()
    }
}


@Composable
fun LoadScreen() {

    ConstraintLayout(
        modifier = Modifier
            .background(YellowContainerLight)
            .fillMaxSize()
    ) {
        val (text, rectangle, beer) = createRefs()

        Text(
            text = "Home",
            style = TextStyle(
                color = Color.Black,
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
                .padding(top = 150.dp)
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
                cornerRadius = CornerRadius(50.dp.toPx(), 50.dp.toPx()),
            )
        }

        Image(
            painter = painterResource(id = app.halfmouth.android.R.drawable.bottle_beer),
            contentDescription = "Logo",
            modifier = Modifier
                .padding(top = 100.dp, end = 0.dp)
                .constrainAs(beer) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
        )
    }

}