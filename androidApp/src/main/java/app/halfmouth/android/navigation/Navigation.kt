package app.halfmouth.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import app.halfmouth.android.navigation.NavAnimations.popEnterDownAnimation
import app.halfmouth.android.navigation.NavAnimations.popEnterRightAnimation
import app.halfmouth.android.navigation.NavAnimations.popExitDownAnimation
import app.halfmouth.android.navigation.NavAnimations.popExitRightAnimation
import app.halfmouth.android.navigation.NavAnimations.slideDownExitAnimation
import app.halfmouth.android.navigation.NavAnimations.slideLeftEnterAnimation
import app.halfmouth.android.navigation.NavAnimations.slideRightExitAnimation
import app.halfmouth.android.navigation.NavAnimations.slideUpEnterAnimation
import app.halfmouth.android.screen.ChartScreen
import app.halfmouth.android.screen.HomeScreen
import app.halfmouth.android.screen.ProductionScreen
import app.halfmouth.android.screen.ScreenRoute
import app.halfmouth.android.screen.SignInScreen
import app.halfmouth.android.screen.SplashScreen
import app.halfmouth.android.viewmodel.SharedViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoute.SplashScreen.route) {
        composable(route = ScreenRoute.SplashScreen.route) {
            SplashScreen(navController)
        }

        composable(route = ScreenRoute.SignInScreen.route) {
            SignInScreen(navController)
        }

        composable(route = ScreenRoute.ProfileScreen.route) {
            SignInScreen(navController)
        }

        composable(route = ScreenRoute.NotificationScreen.route) {
            SignInScreen(navController)
        }

        composable(route = ScreenRoute.HomeScreen.route) {
            val sharedViewModel =
                it.sharedViewModel<SharedViewModel>(navController = navController)
            HomeScreen(navController)
        }

        navigation(
            startDestination = ScreenRoute.ProductionScreen.route,
            route = "navigation_production"
        ) {
            composable(
                route = ScreenRoute.ProductionScreen.route,
                enterTransition = slideUpEnterAnimation,
                exitTransition = slideDownExitAnimation,
                popEnterTransition = popEnterDownAnimation,
                popExitTransition = popExitDownAnimation

            ) {
                val sharedViewModel =
                    it.sharedViewModel<SharedViewModel>(navController = navController)
                ProductionScreen(navController)
            }
            composable(
                route = ScreenRoute.ChartScreen.route,
                enterTransition = slideLeftEnterAnimation,
                exitTransition = slideRightExitAnimation,
                popEnterTransition = popEnterRightAnimation,
                popExitTransition = popExitRightAnimation
            ) {
                val sharedViewModel =
                    it.sharedViewModel<SharedViewModel>(navController = navController)
                ChartScreen(navController)
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}
