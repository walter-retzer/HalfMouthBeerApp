package app.halfmouth.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.halfmouth.android.screen.ChartScreen
import app.halfmouth.android.screen.MainScreen
import app.halfmouth.android.screen.ScreenRoute

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.MainScreen.route) {
        composable(route = ScreenRoute.MainScreen.route) {
            MainScreen(navController)
        }
        composable(route = ScreenRoute.ChartScreen.route) {
            ChartScreen(navController)
        }
    }
}
