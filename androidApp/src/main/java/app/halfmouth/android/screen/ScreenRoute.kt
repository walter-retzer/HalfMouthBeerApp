package app.halfmouth.android.screen

sealed class ScreenRoute(val route: String){
    object MainScreen : ScreenRoute("main_screen")
    object ChartScreen : ScreenRoute("chart_screen")
}
