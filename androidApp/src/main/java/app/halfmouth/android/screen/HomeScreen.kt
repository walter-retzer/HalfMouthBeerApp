package app.halfmouth.android.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import app.halfmouth.android.components.BottomBarMenu
import app.halfmouth.theme.OnBackgroundDark
import app.halfmouth.theme.YellowContainerLight

@Composable
fun HomeScreen(navController: NavHostController) {

    BackHandler {  }

    MyApplicationTheme {
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
}


@Composable
fun LoadScreen() {

    ConstraintLayout(
        modifier = Modifier
            .background(YellowContainerLight)
            .fillMaxSize()
    ) {
        val (text, rectangle, card) = createRefs()

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
                cornerRadius = CornerRadius(30.dp.toPx(), 30.dp.toPx()),
            )
        }

        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 155.dp, bottom = 45.dp)
                .constrainAs(card) {
                    top.linkTo(rectangle.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            items(15) {
                Card(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                        .background(Color.White)
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = 4.dp,
                    backgroundColor = OnBackgroundDark
                ) {}

            }

        }

    }

}
