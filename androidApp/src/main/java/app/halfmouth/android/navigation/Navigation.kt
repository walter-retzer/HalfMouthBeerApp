package app.halfmouth.android.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.halfmouth.R
import app.halfmouth.SharedRes
import app.halfmouth.android.MyApplicationTheme
import app.halfmouth.android.screen.Screen
import app.halfmouth.android.stringResource
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(route = Screen.ChartScreen.route) {
            ChartScreen(navController)
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    MyApplicationTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            color = MaterialTheme.colors.background
        ) {

        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logohalfmouth),
                contentDescription = null,
                modifier = Modifier.clickable(onClick = {
                    navController.navigate(Screen.ChartScreen.route)
                })
            )
            Text(
                text = stringResource(
                    id = SharedRes.strings.halfmouth
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun ChartScreen(navController: NavController) {
    LineChart(
        linesChartData = listOf(
            LineChartData(
                points = listOf(
                    LineChartData.Point(-5.5f, "Label 1"),
                    LineChartData.Point(-3.8f, "Label 2"),
                    LineChartData.Point(-2.5f, "Label 3"),
                    LineChartData.Point(-1.4f, "Label 4")
                ),
                lineDrawer = SolidLineDrawer(),
            )
        ),
        modifier = Modifier
            .height(150.dp),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(),
        horizontalOffset = 5f,
        labels = listOf("08:00", "08:05", "08:10", "08:15")
    )
}