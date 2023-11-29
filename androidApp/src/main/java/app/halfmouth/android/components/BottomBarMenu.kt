package app.halfmouth.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import app.halfmouth.android.screen.ScreenRoute
import app.halfmouth.android.screen.listOfBottomBarScreen
import app.halfmouth.theme.YellowContainerLight

@Composable
fun BottomBarMenu(navController: NavHostController) {
    val screens = listOfBottomBarScreen()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(elevation = 0.dp) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: ScreenRoute,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val destinationRoute = currentDestination?.route

    BottomNavigationItem(
        modifier = Modifier
            .then(Modifier.weight(1.0f))
            .background(Color.Black),
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
        selectedContentColor = YellowContainerLight,
        selected = currentDestination?.hierarchy?.any { destinationRoute == screen.route } == true,
        unselectedContentColor = YellowContainerLight,
        onClick = { navigateToScreen(screen, navController) },
        alwaysShowLabel = true
    )
}

private fun navigateToScreen(screen: ScreenRoute, navController: NavHostController) {
    val route = screen.route
    navController.navigate(route) {
        popUpTo(navController.graph.findStartDestination().id)
        launchSingleTop = true
        restoreState = true
    }
}
