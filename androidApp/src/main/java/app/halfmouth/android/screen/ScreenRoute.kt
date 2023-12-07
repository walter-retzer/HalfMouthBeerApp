package app.halfmouth.android.screen

import app.halfmouth.android.R

sealed class ScreenRoute(
    val route: String,
    val title: String,
    val icon: Int,
    var badgeCount: Int = 1
) {
    object SplashScreen : ScreenRoute("splash_screen", "Splash", R.drawable.icon_water)
    object HomeScreen : ScreenRoute("home_screen", "Home", R.drawable.icon_home)
    object ProductionScreen : ScreenRoute("production_screen", "Produção", R.drawable.icon_beer)
    object ChartScreen : ScreenRoute("chart_screen", "Gráficos", R.drawable.icon_chart)
    object ProfileScreen : ScreenRoute("profile_screen", "Perfil", R.drawable.icon_account)
    object SignInScreen : ScreenRoute("sign_in_screen", "SignIn", R.drawable.icon_account)
    object NotificationScreen :
        ScreenRoute("notifications_screen", "Notificações", R.drawable.icon_notifications, 5)
}

fun listOfBottomBarScreen(): List<ScreenRoute> = listOf(
    ScreenRoute.HomeScreen,
    ScreenRoute.ProductionScreen,
    ScreenRoute.ProfileScreen,
    ScreenRoute.NotificationScreen,
)
