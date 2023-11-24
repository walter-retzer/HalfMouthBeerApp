package app.halfmouth.android.screen

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Shapes
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.halfmouth.android.R
import app.halfmouth.android.data.api.ApiService
import app.halfmouth.android.data.remote.Feeds
import app.halfmouth.android.data.remote.FeedsThingSpeak
import app.halfmouth.android.data.remote.ThingSpeakResponse
import app.halfmouth.core.Strings
import app.halfmouth.theme.DarkColorScheme
import app.halfmouth.theme.LightColorScheme
import app.halfmouth.theme.OnBackgroundDark
import app.halfmouth.theme.OnSurfaceVariantLight
import app.halfmouth.theme.SurfaceVariantDark
import app.halfmouth.theme.TypographyDefault
import app.halfmouth.theme.YellowContainerLight
import dev.icerock.moko.resources.StringResource


@Composable
fun MainScreen(navController: NavController) {

    val service = ApiService.create()
    val initialValue = ThingSpeakResponse(
        channel = null,
        feeds = emptyList()
    )
    val requestValuesOnThingSpeak = produceState(
        initialValue = initialValue,
        producer = { value = service.getThingSpeakValues("2") }
    )

    val mutableListThing = mutableListOf(
        ThingSpeakResponse(
            channel = null,
            feeds = listOf(
                FeedsThingSpeak(
                    created_at = "",
                    entry_id = 1,
                    field1 = "25.223333",
                    field2 = "25.000000",
                    field3 = "25.000000",
                    field4 = "25.000000",
                    field5 = "25.000000",
                    field6 = "25.000000",
                    field8 = "25.000000",
                    field7 = "25.000000",
                ),
                FeedsThingSpeak(
                    created_at = "",
                    entry_id = 1,
                    field1 = "25.000000",
                    field2 = "25.000000",
                    field3 = "25.000000",
                    field4 = "25.000000",
                    field5 = "25.000000",
                    field6 = "25.000000",
                    field8 = "25.000000",
                    field7 = "25.000000",
                ),
            )
        )
    )

    MyApplicationTheme {
        Scaffold(
            scaffoldState = rememberScaffoldState(),
            bottomBar = {
                BottomBar(navController = rememberNavController()) { }
            }
        ) {
            Column(
                modifier = Modifier
                    .background(SurfaceVariantDark)
                    .padding(it)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(Color.Black)
                ) {
                    Text(
                        text = "Equipamentos",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = YellowContainerLight,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif
                        )
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val listReceive = mutableListOf(requestValuesOnThingSpeak.value)
                    //val listReceive = mutableListThing
                    var newList = mutableListOf<Feeds>()
                    listReceive.forEach { response ->
                        if (response.feeds.isNullOrEmpty().not()) {
                            val i1 = if (response.feeds.first()?.field1 == null) 1 else 0
                            val i2 = if (response.feeds.first()?.field5 == null) 1 else 0
                            newList = mutableListOf(
                                Feeds(
                                    fieldName = response.channel?.field1,
                                    fieldValue = response.feeds[i1]?.field1,
                                    fieldData = response.feeds[i1]?.created_at
                                ),
                                Feeds(
                                    fieldName = response.channel?.field2,
                                    fieldValue = response.feeds[i1]?.field2,
                                    fieldData = response.feeds[i1]?.created_at
                                ),
                                Feeds(
                                    fieldName = response.channel?.field3,
                                    fieldValue = response.feeds[i1]?.field3,
                                    fieldData = response.feeds[i1]?.created_at
                                ),
                                Feeds(
                                    fieldName = response.channel?.field4,
                                    fieldValue = response.feeds[i1]?.field4,
                                    fieldData = response.feeds[i1]?.created_at
                                ),
                                Feeds(
                                    fieldName = response.channel?.field5,
                                    fieldValue = response.feeds[i2]?.field5,
                                    fieldData = response.feeds[i2]?.created_at
                                ),
                                Feeds(
                                    fieldName = response.channel?.field6,
                                    fieldValue = response.feeds[i1]?.field6,
                                    fieldData = response.feeds[i1]?.created_at
                                ),
                                Feeds(
                                    fieldName = response.channel?.field7,
                                    fieldValue = response.feeds[i1]?.field7,
                                    fieldData = response.feeds[i1]?.created_at
                                ),
                                Feeds(
                                    fieldName = response.channel?.field8,
                                    fieldValue = response.feeds[i2]?.field8,
                                    fieldData = response.feeds[i2]?.created_at
                                ),
                            )

                            items(newList) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Card(
                                    modifier = Modifier
                                        .padding(
                                            top = 7.dp,
                                            bottom = 7.dp,
                                            start = 12.dp,
                                            end = 12.dp
                                        ),
                                    shape = RoundedCornerShape(16.dp),
                                    elevation = 4.dp,
                                    backgroundColor = OnBackgroundDark
                                ) {

                                    Row(
                                        modifier = Modifier
                                            .padding(
                                                top = 24.dp,
                                                end = 16.dp,
                                            )
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(
                                                id = R.drawable.icon_chart
                                            ),
                                            contentDescription = null,
                                            modifier = Modifier.padding(5.dp),
                                            alignment = Alignment.BottomEnd,
                                        )
                                    }

                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier
                                                .padding(
                                                    start = 16.dp,
                                                    top = 16.dp,
                                                    end = 0.dp,
                                                    bottom = 4.dp
                                                )
                                                .fillMaxSize(),
                                        ) {
                                            val drawable =
                                                if (it.fieldName.toString() == "CAMARA FRIA") R.drawable.icon_freezer
                                                else if (it.fieldName.toString() == "CHILLER") R.drawable.icon_freezer
                                                else if (it.fieldName.toString() == "BOMBA RECIRCULAÇÃO") R.drawable.icon_freezer
                                                else R.drawable.icon_thermostat
                                            Image(
                                                painter = painterResource(
                                                    id = drawable
                                                ),
                                                contentDescription = null,
                                                modifier = Modifier.padding(5.dp)
                                            )
                                            val name =
                                                if (it.fieldName.toString() == "BOMBA RECIRCULAÇÃO") "BOMBA"
                                                else it.fieldName.toString()
                                            Text(
                                                text = name,
                                                style = TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    fontFamily = FontFamily.SansSerif
                                                )
                                            )
                                            var valueFormatted = ""
                                            try {
                                                val value = it.fieldValue.toString().toDouble()
                                                valueFormatted = String.format("%.2f", value)
                                            } catch (e: Exception) {
                                                println("Error: $e")
                                            }
                                            val text =
                                                when (it.fieldValue) {
                                                    "0.00000" -> " = 0"
                                                    "1.00000" -> " = 1"
                                                    else -> " = $valueFormatted°C"
                                                }
                                            Text(
                                                text = text,
                                                style = TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Bold,
                                                )
                                            )
                                        }

                                        Row(
                                            modifier = Modifier
                                                .padding(
                                                    start = 24.dp,
                                                    top = 0.dp,
                                                    end = 16.dp,
                                                    bottom = 8.dp
                                                )
                                                .fillMaxSize(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                        ) {
                                            Text(
                                                text = "Data: " + it.fieldData.toString(),
                                                style = TextStyle(
                                                    color = OnSurfaceVariantLight,
                                                    fontSize = 14.sp,
                                                    fontWeight = FontWeight.Normal,
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

//    ThemeApp(
//        darkTheme = isSystemInDarkTheme(),
//        dynamicColor = true,
//    )
}


@Composable
fun stringResource(id: StringResource, vararg args: Any): String {
    return Strings(LocalContext.current).get(id, args.toList())
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val dynamicColor = true
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = Color.Black.toArgb()
//            WindowCompat.getInsetsController(
//                window,
//                view
//            ).isAppearanceLightStatusBars = darkTheme
//        }
//    }

    val typography = TypographyDefault
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )
    androidx.compose.material3.MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = typography,
        content = content
    )
}

@Composable
fun BottomBar(navController: NavHostController, onNavigate: (AddFloatingButton: Boolean) -> Unit) {
    val screens = listOfBottomBarScreen()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(elevation = 0.dp) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
                onNavigate = onNavigate
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: ScreenRoute,
    currentDestination: NavDestination?,
    navController: NavHostController,
    onNavigate: (AddFloatingButton: Boolean) -> Unit,
) {
    val destinationRoute = currentDestination?.route?.replace("/{isEdit}", "")
    BottomNavigationItem(
        modifier = Modifier
            .then(Modifier.weight(1.0f))
            .background(Color.Black),
        label = {
            Text(
                text = screen.title,
                textAlign = TextAlign.Center,
                softWrap = false,
                fontSize = 10.sp
            )
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(screen.icon),
                contentDescription = null
            )
        },
        selectedContentColor = YellowContainerLight,
        selected = currentDestination?.hierarchy?.any { destinationRoute == screen.route } == true,
        unselectedContentColor = YellowContainerLight,
        onClick = {},
        alwaysShowLabel = true
    )
}
