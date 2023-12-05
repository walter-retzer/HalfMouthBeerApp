package app.halfmouth.android.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import app.halfmouth.android.data.ingridients.BeerTypes
import app.halfmouth.android.data.ingridients.Ingredients
import app.halfmouth.theme.OnSurfaceDark
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
                subListHalfMouthContact()
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
        val mock = listOf(
            Ingredients("Lúpulo", "", R.drawable.lupulo),
            Ingredients("Malte", "", R.drawable.malte),
            Ingredients("Agua", "", R.drawable.agua),
            Ingredients("Leveduras", "", R.drawable.leveduras),
        )
        items(mock.size) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                val (text, image) = createRefs()
                Image(
                    painter = painterResource(id = mock[it].image),
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
                    text = mock[it].name,
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
    val mock = listOf(
        BeerTypes(
            "Pilsen",
            "Também chamado de Pilsner, o estilo surgiu na cidade de Pilsen, região da Bohemia, na República Tcheca, com a criação da cerveja Pilsner Urquell, em 1842, a primeira Pilsen da história. As cervejas Pilsen são caracterizadas por um lúpulo acentuado no aroma e sabor e por sua cor dourada brilhante.",
            R.drawable.beer_mug_ipa
        ),
        BeerTypes(
            "Session Ipa",
            "Cerveja leve e cítrica, a Session IPA traz um frescor ao paladar, mesmo com amargor e aroma de lúpulo bem presentes. A versão mais suave da American IPA é ideal para se iniciar nas cervejas ale.",
            R.drawable.beermug
        ),
        BeerTypes(
            "Ipa",
            "O estilo IPA é marcado pelo sua alta concentração de lúpulos, o que faz com que seu sabor seja mais puxado para o amargor. Sua graduação alcoólica também é um pouco maior que o normal. É muito comum a utilização da técnica de “dry hopping” neste estilo, o que consiste em adicionar mais lúpulo ao mosto durante a fase de maturação ou fermentação do mosto. Isso adiciona mais frescor e aroma à cerveja, sem aumentar muito seu amargor.",
            R.drawable.beer_craft_glass
        ),
    )
    items(mock.size) {
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
            Image(
                painter = painterResource(id = R.drawable.brewingbeer),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp, bottom = 8.dp)
                    .clip(RoundedCornerShape(16))
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "Fazemos cervejas com a mais alta qualidade, atualmente produzimos 3 estilos de cerveja, sendo eles: PILSEN, SESSION IPA e IPA.",
                style = TextStyle(
                    color = OnSurfaceVariantDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center
                ),
            )
        }
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = mock[it].name,
            style = TextStyle(
                color = OnSurfaceVariantDark,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            ),
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = mock[it].description,
            style = TextStyle(
                color = OnSurfaceVariantDark,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            ),
        )
        Image(
            painter = painterResource(id = mock[it].image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, bottom = 8.dp)
                .clip(RoundedCornerShape(16))
        )
    }
}


fun LazyListScope.subListHalfMouthContact() {
    items(1) {
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Nosso Contato:",
            style = TextStyle(
                color = OnSurfaceVariantDark,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            ),
        )
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 4.dp, bottom = 4.dp),
            text = "Solicite um orçamento através dos canais abaixo.",
            style = TextStyle(
                color = OnSurfaceVariantDark,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Start
            ),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .clickable { }
                    .padding(top = 5.dp),
                painter = painterResource(id = R.drawable.icon_phone),
                contentDescription = null,
                tint = OnSurfaceVariantDark
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = "+55 15 99999-9999",
                style = TextStyle(
                    color = OnSurfaceVariantDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center
                ),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .clickable { }
                    .padding(top = 5.dp),
                painter = painterResource(id = R.drawable.icon_email),
                contentDescription = null,
                tint = OnSurfaceVariantDark
            )
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentHeight(),
                text = "halfmouth@halmouth.com.br",
                style = TextStyle(
                    color = OnSurfaceVariantDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start
                ),
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = "Nosso Endereço:",
            style = TextStyle(
                color = OnSurfaceVariantDark,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            ),
        )
        Text(
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 4.dp, bottom = 4.dp),
            text = "Venha encher os seus growlers.",
            style = TextStyle(
                color = OnSurfaceVariantDark,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Start
            ),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .clickable { }
                    .padding(top = 5.dp),
                painter = painterResource(id = R.drawable.icon_location),
                contentDescription = null,
                tint = OnSurfaceVariantDark
            )
            Text(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .wrapContentHeight(),
                text = "Rua João Martini Filho, 426 - Jardim São Conrado, Sorocaba-SP.",
                style = TextStyle(
                    color = OnSurfaceVariantDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start
                ),
            )
        }
    }
}
