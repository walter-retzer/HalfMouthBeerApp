package app.halfmouth.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import app.halfmouth.android.navigation.NavAnimations.slideDownExitAnimation
import app.halfmouth.android.navigation.NavAnimations.slideUpEnterAnimation
import app.halfmouth.android.screen.ChartScreen
import app.halfmouth.android.screen.HomeScreen
import app.halfmouth.android.screen.ProductionScreen
import app.halfmouth.android.screen.ScreenRoute
import app.halfmouth.android.screen.SplashScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.SplashScreen.route) {
        composable(route = ScreenRoute.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(route = ScreenRoute.HomeScreen.route) {
            HomeScreen(navController)
        }
        navigation(
            startDestination = ScreenRoute.ProductionScreen.route,
            route = "navigation_production"
        ) {

            composable(
                route = ScreenRoute.ProductionScreen.route,
                enterTransition = slideUpEnterAnimation,
                exitTransition = slideDownExitAnimation

            ) {
                ProductionScreen(navController)
            }
            composable(route = ScreenRoute.ChartScreen.route) {
                ChartScreen(navController)
            }
        }
    }
}
