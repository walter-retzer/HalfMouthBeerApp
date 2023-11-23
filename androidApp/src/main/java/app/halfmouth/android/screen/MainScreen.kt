package app.halfmouth.android.screen

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Shapes
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.halfmouth.ThemeApp
import app.halfmouth.android.data.api.ApiService
import app.halfmouth.android.data.remote.Feeds
import app.halfmouth.android.data.remote.FeedsThingSpeak
import app.halfmouth.android.data.remote.ThingSpeakResponse
import app.halfmouth.core.Strings
import app.halfmouth.theme.BackgroundDark
import app.halfmouth.theme.DarkColorScheme
import app.halfmouth.theme.LightColorScheme
import app.halfmouth.theme.TypographyDefault
import app.halfmouth.theme.YellowContainerLight
import app.halfmouth.theme.YellowSecondaryContainerLight
import app.halfmouth.theme.YellowSecondaryLight
import dev.icerock.moko.resources.StringResource


@Composable
fun MainScreen(navController: NavController) {

    val service = ApiService.create()
    val initialValue = ThingSpeakResponse(
        channel = null,
        feeds = emptyList()
    )
//    val requestValuesOnThingSpeak = produceState(
//        initialValue = initialValue,
//        producer = { value = service.getThingSpeakValues("2") }
//    )

    val mutableListThing = mutableListOf(
        ThingSpeakResponse(
            channel = null,
            feeds = listOf(
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
            Column(modifier = Modifier.padding(it)) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(YellowContainerLight)
                ) {
                    Text(
                        text = "Equipamentos",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }


//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.White)
//                ) {


                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    // val listReceive = mutableListOf(requestValuesOnThingSpeak.value)
                    val listReceive = mutableListThing
                    var newList = mutableListOf<Feeds>()
                    listReceive.forEach {
                        if (it.feeds.isNullOrEmpty().not()) {
                            val i1 = if (it.feeds.first()?.field1 == null) 1 else 0
                            val i2 = if (it.feeds.first()?.field5 == null) 1 else 0
                            newList = mutableListOf(
                                Feeds(
                                    fieldName = it.channel?.field1,
                                    fieldValue = it.feeds[i1]?.field1,
                                    fieldData = it.feeds[i1]?.created_at
                                ),
                                Feeds(
                                    fieldName = it.channel?.field2,
                                    fieldValue = it.feeds[i1]?.field2,
                                    fieldData = it.feeds[i1]?.created_at
                                ),
                                Feeds(
                                    fieldName = it.channel?.field3,
                                    fieldValue = it.feeds[i1]?.field3,
                                    fieldData = it.feeds[i1]?.created_at
                                ),
                                Feeds(
                                    fieldName = it.channel?.field4,
                                    fieldValue = it.feeds[i1]?.field4,
                                    fieldData = it.feeds[i1]?.created_at
                                ),
                                Feeds(
                                    fieldName = it.channel?.field5,
                                    fieldValue = it.feeds[i2]?.field5,
                                    fieldData = it.feeds[i2]?.created_at
                                ),
                                Feeds(
                                    fieldName = it.channel?.field6,
                                    fieldValue = it.feeds[i1]?.field6,
                                    fieldData = it.feeds[i1]?.created_at
                                ),
                                Feeds(
                                    fieldName = it.channel?.field7,
                                    fieldValue = it.feeds[i1]?.field7,
                                    fieldData = it.feeds[i1]?.created_at
                                ),
                                Feeds(
                                    fieldName = it.channel?.field8,
                                    fieldValue = it.feeds[i2]?.field8,
                                    fieldData = it.feeds[i2]?.created_at
                                ),
                            )

                            items(newList) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(4.dp)
                                ) {
                                    Row(
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
                                            if (it.fieldName.toString() == "CAMARA FRIA") app.halfmouth.android.R.drawable.icon_freezer
                                            else if (it.fieldName.toString() == "CHILLER") app.halfmouth.android.R.drawable.icon_freezer
                                            else if (it.fieldName.toString() == "BOMBA RECIRCULAÇÃO") app.halfmouth.android.R.drawable.icon_water
                                            else app.halfmouth.android.R.drawable.icon_thermostat
                                        Image(
                                            painter = painterResource(
                                                id = drawable
                                            ),
                                            contentDescription = null,
                                        )
                                        Text(
                                            text = it.fieldName.toString(),
                                            fontSize = 20.sp
                                        )
                                        val text =
                                            when (it.fieldValue) {
                                                "0.00000" -> " = 0"
                                                "1.00000" -> " = 1"
                                                else -> " = ${it.fieldValue} °C"
                                            }
                                        Text(
                                            text = text,
                                            fontSize = 20.sp
                                        )
                                    }
                                    Row(
                                        modifier = Modifier
                                            .padding(
                                                start = 20.dp,
                                                top = 4.dp,
                                                end = 16.dp,
                                                bottom = 4.dp
                                            )
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                    ) {
                                        Text(
                                            text = "Data: " + it.fieldData.toString(),
                                            fontSize = 12.sp
                                        )
                                    }


                                    Row(
                                        modifier = Modifier
                                            .padding(
                                                start = 20.dp,
                                                top = 4.dp,
                                                end = 16.dp,
                                                bottom = 4.dp
                                            )
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                    ) {
                                        Text(
                                            text = "Data: " + it.fieldData.toString(),
                                            fontSize = 12.sp
                                        )
                                    }


                                    Row(
                                        modifier = Modifier
                                            .padding(
                                                start = 20.dp,
                                                top = 4.dp,
                                                end = 16.dp,
                                                bottom = 4.dp
                                            )
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                    ) {
                                        Text(
                                            text = "Data: " + it.fieldData.toString(),
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }


//}

            }
        }


    }
    ThemeApp(
        darkTheme = isSystemInDarkTheme(),
        dynamicColor = false,
    )
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
    val dynamicColor = false
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(
                window,
                view
            ).isAppearanceLightStatusBars = darkTheme
        }
    }

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
            .background(YellowContainerLight),
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
        selectedContentColor = YellowSecondaryContainerLight,
        selected = currentDestination?.hierarchy?.any { destinationRoute == screen.route } == true,
        unselectedContentColor = YellowSecondaryContainerLight,
        onClick = {},
        alwaysShowLabel = true
    )
}
