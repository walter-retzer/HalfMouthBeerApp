package app.halfmouth.android.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import app.halfmouth.android.R
import app.halfmouth.android.components.BottomBarMenu
import app.halfmouth.theme.OnSurfaceVariantDark
import app.halfmouth.theme.SurfaceVariantDark
import app.halfmouth.theme.YellowContainerLight

@Composable
fun HomeScreen(navController: NavHostController) {
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
                        .padding(top = 50.dp)

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
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.perfil_default),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(35))
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "HalfMouth",
                    style = TextStyle(
                        color = YellowContainerLight,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center
                    ),
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                item { SubItemTitle() }
                item { SubListIngredients() }
                subListBeerProduction()
            }
        }
    }
}

@Composable
fun SubItemTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "Nossos Ingredientes:",
            style = TextStyle(
                color = OnSurfaceVariantDark,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Start
            ),
        )
    }
}

@Composable
fun SubListIngredients() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(10) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                val (text, image) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.lupulo),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(16))
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .constrainAs(text) {
                            top.linkTo(image.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    text = "Lúpulo",
                    style = TextStyle(
                        color = OnSurfaceVariantDark,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center
                    ),
                )
            }
        }
    }
}

fun LazyListScope.subListBeerProduction() {
    items(8) {
        if (it == 0) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Nossas Cervejas:",
                style = TextStyle(
                    color = OnSurfaceVariantDark,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center
                ),
            )
        }
        Image(
            painter = painterResource(id = R.drawable.imagebeerbackground),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
                .clip(RoundedCornerShape(16))
        )
    }
}
