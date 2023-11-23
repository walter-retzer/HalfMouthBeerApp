package app.halfmouth.android.screen

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation

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