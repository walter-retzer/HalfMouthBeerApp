package app.halfmouth.android.screen

sealed class ScreenRoute(val route: String){
    object SplashScreen : ScreenRoute("splash_screen")
    object MainScreen : ScreenRoute("main_screen")
    object ChartScreen : ScreenRoute("chart_screen")
}
