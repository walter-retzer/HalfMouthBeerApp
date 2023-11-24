package app.halfmouth.android.screen

import app.halfmouth.android.R

sealed class ScreenRoute(val route: String, val title: String, val icon: Int) {
    object SplashScreen : ScreenRoute("splash_screen", "Splash", R.drawable.icon_water)
    object MainScreen : ScreenRoute("main_screen", "Home", R.drawable.icon_home)
    object ChartScreen : ScreenRoute("chart_screen", "Gráficos", R.drawable.icon_chart)
    object EquipmentsScreen : ScreenRoute("chart_screen", "Produção", R.drawable.icon_drink)
    object ProfileScreen : ScreenRoute("chart_screen", "Perfil", R.drawable.icon_account)
    object NotificationScreen : ScreenRoute("chart_screen", "Notificações", R.drawable.icon_notifications)
}

fun listOfBottomBarScreen(): List<ScreenRoute> = listOf(
    ScreenRoute.MainScreen,
    ScreenRoute.EquipmentsScreen,
    ScreenRoute.ProfileScreen,
    ScreenRoute.NotificationScreen,
)
