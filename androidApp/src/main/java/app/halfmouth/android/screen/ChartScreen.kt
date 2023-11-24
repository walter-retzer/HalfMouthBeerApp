package app.halfmouth.android.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.halfmouth.theme.OnYellowSecondaryContainerDark
import app.halfmouth.theme.SurfaceVariantDark
import app.halfmouth.theme.YellowContainerLight
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.line.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.line.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation

@Composable
fun ChartScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SurfaceVariantDark)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.Black)
        ) {
            Text(
                text = "Temperatura",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = YellowContainerLight,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }

        LineChart(
            linesChartData = listOf(
                LineChartData(
                    points = listOf(
                        LineChartData.Point(-5.5f, "Label 1"),
                        LineChartData.Point(-3.8f, "Label 2"),
                        LineChartData.Point(-2.5f, "Label 3"),
                        LineChartData.Point(-1.4f, "Label 4")
                    ),
                    lineDrawer = SolidLineDrawer(
                        thickness = 3.dp,
                        color = Color.Yellow
                    ),
                )
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(35.dp)
                .background(color = SurfaceVariantDark),

            animation = simpleChartAnimation(),
            pointDrawer = FilledCircularPointDrawer(
                diameter = 5.dp,
                color = OnYellowSecondaryContainerDark
            ),
            horizontalOffset = 5f,
            labels = listOf("08:00", "08:05", "08:10", "08:15"),
            xAxisDrawer = SimpleXAxisDrawer(
                labelTextColor = Color.White,
                axisLineColor = Color.White
            ),
            yAxisDrawer = SimpleYAxisDrawer(
                labelTextColor = Color.White,
                axisLineColor = Color.White
            ),
        )
    }


}