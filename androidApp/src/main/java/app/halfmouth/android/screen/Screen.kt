package app.halfmouth.android.screen

sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    object ChartScreen : Screen("chart_screen")
}
