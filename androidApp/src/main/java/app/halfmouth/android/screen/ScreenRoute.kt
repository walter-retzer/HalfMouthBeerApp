package app.halfmouth.android.screen

import app.halfmouth.android.R

sealed class ScreenRoute(val route: String, val title: String, val icon: Int) {
    object SplashScreen : ScreenRoute("splash_screen", "Splash", R.drawable.icon_water)
    object MainScreen : ScreenRoute("main_screen", "Menu", R.drawable.icon_water)
    object ChartScreen : ScreenRoute("chart_screen", "Gráficos", R.drawable.icon_water)
}

fun listOfBottomBarScreen(): List<ScreenRoute> = listOf(
    ScreenRoute.MainScreen,
    ScreenRoute.ChartScreen,
    ScreenRoute.MainScreen,
    ScreenRoute.ChartScreen,
)