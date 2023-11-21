package app.halfmouth.android

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Shapes
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import app.halfmouth.SharedRes
import app.halfmouth.ThemeApp
import app.halfmouth.android.data.api.ApiService
import app.halfmouth.android.data.remote.Feeds
import app.halfmouth.android.data.remote.ThingSpeakResponse
import app.halfmouth.core.Strings
import app.halfmouth.theme.DarkColorScheme
import app.halfmouth.theme.LightColorScheme
import app.halfmouth.theme.TypographyDefault
import com.github.tehras.charts.line.LineChart
import com.github.tehras.charts.line.LineChartData
import com.github.tehras.charts.line.renderer.line.SolidLineDrawer
import com.github.tehras.charts.line.renderer.point.FilledCircularPointDrawer
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import dev.icerock.moko.resources.StringResource

class MainActivity : ComponentActivity() {

    private val service = ApiService.create()
    private val initialValue = ThingSpeakResponse(
        channel = null,
        feeds = emptyList()
    )

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val requestValuesOnThingSpeak = produceState(
                initialValue = initialValue,
                producer = { value = service.getThingSpeakValues("2") }
            )

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = app.halfmouth.R.drawable.logohalfmouth),
                            contentDescription = null
                        )
                        Text(
                            text = stringResource(
                                id = SharedRes.strings.halfmouth
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Divider(
                            color = Color.Gray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .padding(4.dp)
                        )
                        LazyColumn {
                            val listReceive = mutableListOf(requestValuesOnThingSpeak.value)
                            var newList = mutableListOf<Feeds>()
                            listReceive.forEach {
                                if (it.feeds.isNullOrEmpty().not()) {
                                    val i1 = if (it.feeds.first()?.field1 == null) 1 else 0
                                    val i2 = if (it.feeds.first()?.field5 == null) 1 else 0
                                    newList = mutableListOf(
                                        Feeds(
                                            fieldName = it.channel?.field1,
                                            fieldValue = it.feeds[i1]?.field1,
                                            fieldData = it.feeds[i1]?.created_at
                                        ),
                                        Feeds(
                                            fieldName = it.channel?.field2,
                                            fieldValue = it.feeds[i1]?.field2,
                                            fieldData = it.feeds[i1]?.created_at
                                        ),
                                        Feeds(
                                            fieldName = it.channel?.field3,
                                            fieldValue = it.feeds[i1]?.field3,
                                            fieldData = it.feeds[i1]?.created_at
                                        ),
                                        Feeds(
                                            fieldName = it.channel?.field4,
                                            fieldValue = it.feeds[i1]?.field4,
                                            fieldData = it.feeds[i1]?.created_at
                                        ),
                                        Feeds(
                                            fieldName = it.channel?.field5,
                                            fieldValue = it.feeds[i2]?.field5,
                                            fieldData = it.feeds[i2]?.created_at
                                        ),
                                        Feeds(
                                            fieldName = it.channel?.field6,
                                            fieldValue = it.feeds[i1]?.field6,
                                            fieldData = it.feeds[i1]?.created_at
                                        ),
                                        Feeds(
                                            fieldName = it.channel?.field7,
                                            fieldValue = it.feeds[i1]?.field7,
                                            fieldData = it.feeds[i1]?.created_at
                                        ),
                                        Feeds(
                                            fieldName = it.channel?.field8,
                                            fieldValue = it.feeds[i2]?.field8,
                                            fieldData = it.feeds[i2]?.created_at
                                        ),
                                    )

                                    items(newList) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(4.dp)
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .padding(
                                                        start = 16.dp,
                                                        top = 16.dp,
                                                        end = 0.dp,
                                                        bottom = 4.dp
                                                    )
                                                    .fillMaxSize(),
                                            ) {
                                                val drawable =
                                                    if (it.fieldName.toString() == "CAMARA FRIA") R.drawable.icon_freezer
                                                    else if (it.fieldName.toString() == "CHILLER") R.drawable.icon_freezer
                                                    else if (it.fieldName.toString() == "BOMBA RECIRCULAÇÃO") R.drawable.icon_water
                                                    else R.drawable.icon_thermostat
                                                Image(
                                                    painter = painterResource(
                                                        id = drawable
                                                    ),
                                                    contentDescription = null
                                                )
                                                Text(
                                                    text = it.fieldName.toString(),
                                                    fontSize = 20.sp
                                                )
                                                val text =
                                                    when (it.fieldValue) {
                                                        "0.00000" -> " = 0"
                                                        "1.00000" -> " = 1"
                                                        else -> " = ${it.fieldValue} °C"
                                                    }
                                                Text(
                                                    text = text,
                                                    fontSize = 20.sp
                                                )
                                            }
                                            Row(
                                                modifier = Modifier
                                                    .padding(
                                                        start = 20.dp,
                                                        top = 4.dp,
                                                        end = 16.dp,
                                                        bottom = 4.dp
                                                    )
                                                    .fillMaxSize(),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                            ) {
                                                Text(
                                                    text = "Data: " + it.fieldData.toString(),
                                                    fontSize = 12.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }


//                        Box(
//                            modifier = Modifier
//                                .padding(16.dp)
//                                .fillMaxSize(),
//                            contentAlignment = Alignment.BottomEnd,
//                        ) {
//                            FloatingActionButton(
//                                onClick = {},
//                                shape = RoundedCornerShape(32.dp),
//                                modifier = Modifier,
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Home,
//                                    contentDescription = "Home"
//                                )
//                            }
//                        }
                    }
                }
                ThemeApp(
                    darkTheme = isSystemInDarkTheme(),
                    dynamicColor = true,
                )
            }
        }
    }
}

@Composable
fun LineChartCompose() {
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

@Composable
fun stringResource(id: StringResource, vararg args: Any): String {
    return Strings(LocalContext.current).get(id, args.toList())
}

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val dynamicColor = true
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(
                window,
                view
            ).isAppearanceLightStatusBars = darkTheme
        }
    }

    val typography = TypographyDefault
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )
    androidx.compose.material3.MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = typography,
        content = content
    )
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("HalfMouth")
    }
}