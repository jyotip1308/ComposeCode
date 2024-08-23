package com.composemates.composecode.charts

import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine

/*
@Composable
fun LineChartScreen(){
    val steps = 5
    val pointsData = listOf(
        Point(0f,40f),
        Point(1f,40f),
        Point(2f,40f),
        Point(3f,40f),
        Point(4f,40f)
    )

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Transparent)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = 100 / steps
            (i * yScale).toString()
        }
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = MaterialTheme.colorScheme.tertiary,
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = MaterialTheme.colorScheme.tertiary
                    ),
                    SelectionHighlightPoint(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                               Color.Cyan,
                                Color.LightGray
                            )
                        )
                    ),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = MaterialTheme.colorScheme.outline)
    )


    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = lineChartData
    )
}*/

/*@Composable
fun LineChartScreen() {
    val steps = 5
    val pointsData = listOf(
        Point(0f, 40f),
        Point(1f, 40f),
        Point(2f, 40f),
        Point(3f, 40f),
        Point(4f, 40f)
    )

    // Log data points to verify
    pointsData.forEach { point ->
        Log.d("LineChartScreen", "Point: x=${point.x}, y=${point.y}")
    }

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Transparent)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = 100 / steps
            (i * yScale).toString()
        }
        .axisLineColor(MaterialTheme.colorScheme.tertiary)
        .axisLabelColor(MaterialTheme.colorScheme.tertiary)
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = MaterialTheme.colorScheme.tertiary,
                        lineType = LineType.SmoothCurve(isDotted = false)
                    ),
                    IntersectionPoint(
                        color = MaterialTheme.colorScheme.tertiary
                    ),
                    SelectionHighlightPoint(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    ShadowUnderLine(
                        alpha = 0.5f,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Cyan,
                                Color.LightGray
                            )
                        )
                    ),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        backgroundColor = MaterialTheme.colorScheme.surface,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = MaterialTheme.colorScheme.outline)
    )

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = lineChartData
    )
}*/

/*
@Composable
fun LineChart(points: List<Pair<Float, Float>>, labels: List<String>, modifier: Modifier = Modifier) {
    val padding = 16.dp
    val maxValue = points.maxOf { it.second }
    val minValue = points.minOf { it.second }

    Canvas(modifier = modifier.padding(padding)) {
        val xAxisSpacing = (size.width - 2 * padding.toPx()) / (points.size - 1)
        val yAxisHeight = size.height - 2 * padding.toPx()
        val yRange = maxValue - minValue

        // Draw Grid Lines
        val stepCount = 6
        val yStep = yAxisHeight / stepCount
        val yValueStep = yRange / stepCount

        for (i in 0..stepCount) {
            val y = padding.toPx() + i * yStep
            drawLine(
                color = Color.LightGray,
                start = androidx.compose.ui.geometry.Offset(padding.toPx(), y),
                end = androidx.compose.ui.geometry.Offset(size.width - padding.toPx(), y),
                strokeWidth = 2f
            )
        }

        // Draw Line
        for (i in 0 until points.size - 1) {
            val x1 = padding.toPx() + i * xAxisSpacing
            val y1 = size.height - padding.toPx() - (points[i].second - minValue) / yRange * yAxisHeight
            val x2 = padding.toPx() + (i + 1) * xAxisSpacing
            val y2 = size.height - padding.toPx() - (points[i + 1].second - minValue) / yRange * yAxisHeight

            drawLine(
                color = Color.Blue,
                start = androidx.compose.ui.geometry.Offset(x1, y1),
                end = androidx.compose.ui.geometry.Offset(x2, y2),
                strokeWidth = 4f
            )

            drawCircle(
                color = Color.Red,
                center = androidx.compose.ui.geometry.Offset(x1, y1),
                radius = 8f
            )

            if (i == points.size - 2) {
                drawCircle(
                    color = Color.Red,
                    center = androidx.compose.ui.geometry.Offset(x2, y2),
                    radius = 8f
                )
            }
        }

        // Draw X-axis Labels
        for (i in labels.indices) {
            val x = padding.toPx() + i * xAxisSpacing
            drawContext.canvas.nativeCanvas.drawText(
                labels[i],
                x,
                size.height - padding.toPx() + 20f,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.BLACK
                    textSize = 32f
                    textAlign = android.graphics.Paint.Align.CENTER
                }
            )
        }
    }
}
*/

/*@Composable
fun LineChartDemo2() {
    val pointsData = listOf(
        DataPoint(0f, 10f),
        DataPoint(1f, 15f),
        DataPoint(2f, 20f),
        DataPoint(3f, 25f),
        DataPoint(4f, 30f)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LineGraph(
            plot = LinePlot(
                lines = listOf(
                    LinePlot.Line(
                        pointsData,
                        LinePlot.Connection(color = Color.Blue),
                        LinePlot.Intersection(color = Color.Red),
                        LinePlot.Highlight(color = Color.Yellow)
                    )
                ),
                grid = LinePlot.Grid(Color.LightGray, steps = 6),
                xAxis = LinePlot.XAxis(stepSize = 1.dp)
            ),
            modifier = Modifier
                .padding(
                    top = MaterialTheme.dimens.largePadding,
                    end = MaterialTheme.dimens.mediumPadding
                )
                .fillMaxWidth()
                .height(400.dp)
        )

        // Custom X-Axis Labels
        Canvas(modifier = Modifier
            .padding(
                top = MaterialTheme.dimens.largePadding,
                end = MaterialTheme.dimens.mediumPadding
            )
            .fillMaxWidth()
            .height(400.dp)
        ) {
            val labelPositions = listOf(0f, 1f, 2f, 3f, 4f)
            val labels = listOf("0","1", "2", "3", "4")
            val yOffset = size.height + 10f // Adjust the yOffset as needed

            drawIntoCanvas { canvas ->
                labelPositions.forEachIndexed { index, position ->
                    val x = position / 4 * size.width
                    val label = labels[index]
                    canvas.nativeCanvas.drawText(
                        label,
                        x,
                        yOffset,
                        android.graphics.Paint().apply {
                            color = android.graphics.Color.BLACK
                            textSize = 14.sp.toPx()
                        }
                    )
                }
            }
        }
    }
}*/


/*@Composable
fun LineChartDemo() {
    val entryModelProducer = ChartEntryModelProducer(
        listOf(
            entryOf(x = 0f, y = 10f),
            entryOf(x = 1f, y = 15f),
            entryOf(x = 2f, y = 20f),
            entryOf(x = 3f, y = 25f),
            entryOf(x = 4f, y = 30f)
        )
    )

    Chart(
        chart = LineChart(
            lines = listOf(
                LineChart.Line(
                    producer = entryModelProducer,
                    curved = false // Ensure the line is straight
                )
            )
        ),
        modifier = Modifier.fillMaxSize(),
        startAxis = startAxis(),
        bottomAxis = bottomAxis()
    )
}*/

/*@Composable
fun LineChartCanvas(pointsData: List<DataPoint>) {
    Canvas(
        modifier = Modifier
            .padding(top = 16.dp, end = 8.dp)
            .fillMaxWidth()
            .height(400.dp)
    ) {
        val padding = 50f
        val xMax = 30f
        val yMax = pointsData.maxOfOrNull { it.y } ?: 0f
        val xStep = (size.width - 2 * padding) / xMax
        val yStep = (size.height - 2 * padding) / yMax

        // Draw grid lines
        for (i in 0..30) {
            val x = padding + i * xStep
            drawLine(
                color = Color.LightGray,
                start = Offset(x, padding),
                end = Offset(x, size.height - padding)
            )
        }

        for (i in 0..4) {
            val y = padding + i * yStep
            drawLine(
                color = Color.LightGray,
                start = Offset(padding, y),
                end = Offset(size.width - padding, y)
            )
        }

        // Draw line chart
        val points = pointsData.map { point ->
            Offset(
                padding + point.x * xStep,
                size.height - padding - point.y * yStep
            )
        }
        for (i in 1 until points.size) {
            drawLine(
                color = Color.Blue,
                start = points[i - 1],
                end = points[i],
                strokeWidth = 5f
            )
        }

        // Draw points
        points.forEach { point ->
            drawCircle(
                color = Color.Red,
                center = point,
                radius = 8f
            )
        }

        // Draw x-axis labels
        for (i in 0..30) {
            val x = padding + i * xStep
            drawContext.canvas.nativeCanvas.drawText(
                i.toString(),
                x,
                size.height - padding / 2,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.BLACK
                    textSize = 20f
                    textAlign = android.graphics.Paint.Align.CENTER
                }
            )
        }

        // Draw y-axis labels
        for (i in 0..4) {
            val y = padding + i * yStep
            drawContext.canvas.nativeCanvas.drawText(
                (yMax * (4 - i) / 4).toInt().toString(),
                padding / 2,
                y,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.BLACK
                    textSize = 20f
                    textAlign = android.graphics.Paint.Align.CENTER
                }
            )
        }
    }
}*/

/*@Composable
internal fun LineGraph1(item: List<List<DataPoint>>) {
    LineGraph(
        plot = LinePlot(
            listOf(
                LinePlot.Line(
                    item[0],
                    LinePlot.Connection(green, 2.dp),
                    LinePlot.Intersection(blue, 5.dp),
                    LinePlot.Highlight(darkBlue, 5.dp),
                ),
                LinePlot.Line(
                    item[1],
                    LinePlot.Connection(Color.Gray, 2.dp),
                    LinePlot.Intersection { center, _ ->
                        val px = 2.dp.toPx()
                        val topLeft = Offset(center.x - px, center.y - px)
                        drawRect(Color.Gray, topLeft, Size(px * 2, px * 2))
                    },
                ),
            ),
            LinePlot.Grid(Color.Gray),
            selection = LinePlot.Selection(
                highlight = LinePlot.Connection(
                    red,
                    strokeWidth = 2.dp,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(40f, 20f))
                )
            ),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}*/

/*object DataPoints {
    val dataPoints1 = listOf(
        DataPoint(0f, 0f),
        DataPoint(1f, 0f),
        DataPoint(2f, 0f),
        DataPoint(3f, 0f),
        DataPoint(4f, 0f),
        DataPoint(5f, 25f),
        DataPoint(
            6f,
            75f
        ),
        DataPoint(7f, 100f),
        DataPoint(8f, 80f),
        DataPoint(9f, 75f),
        DataPoint(10f, 55f),
        DataPoint(11f, 45f),
        DataPoint(12f, 50f),
        DataPoint(13f, 80f),
        DataPoint(14f, 70f),
        DataPoint(15f, 25f),
        DataPoint(16f, 0f), // FIXME :Bug: Change this to 200f. Column doesn't adapt.
        DataPoint(17f, 0f),
        DataPoint(18f, 35f),
        DataPoint(19f, 60f),
        DataPoint(20f, 20f),
        DataPoint(21f, 40f),
        DataPoint(22f, 75f),
        DataPoint(23f, 50f)
    )

    val dataPoints2 = listOf(
        DataPoint(0f, 0f),
        DataPoint(1f, 0f),
        DataPoint(2f, 25f),
        DataPoint(3f, 75f),
        DataPoint(4f, 100f),
        DataPoint(5f, 80f),
        DataPoint(6f, 75f),
        DataPoint(7f, 50f),
        DataPoint(8f, 80f),
        DataPoint(9f, 70f),
        DataPoint(10f, 0f),
        DataPoint(11f, 0f),
        DataPoint(12f, 45f),
        DataPoint(13f, 20f),
        DataPoint(14f, 40f),
        DataPoint(15f, 75f),
        DataPoint(16f, 50f),
        DataPoint(17f, 75f),
        DataPoint(18f, 40f),
        DataPoint(19f, 20f),
        DataPoint(20f, 0f),
        DataPoint(21f, 0f),
        DataPoint(22f, 50f),
        DataPoint(23f, 25f)
    )
}*/

/*  val pointsData = listOf(
        DataPoint(0f, 0f),
        DataPoint(1f, 2f),
        DataPoint(2f, 4f),
        DataPoint(3f, 6f),
        DataPoint(4f, 60f) ,
        DataPoint(5f, 0f),
        DataPoint(6f, 2f),
        DataPoint(7f, 4f),
        DataPoint(8f, 6f),
        DataPoint(9f, 60f) ,
        DataPoint(10f, 0f),
        DataPoint(11f, 2f),
        DataPoint(12f, 4f),
        DataPoint(13f, 6f),
        DataPoint(14f, 60f) // Example data point at x = 30
    )*/


/*@Composable
fun LineChart(pointsData: List<DataPoint>) {

    LineGraph(
        plot = LinePlot(
            lines = listOf(
                LinePlot.Line(
                    pointsData,
                    LinePlot.Connection(color = Color.Blue),
                    LinePlot.Intersection(color = Color.Red),
                    LinePlot.Highlight(color = Color.Yellow)
                )
            ),
            grid = LinePlot.Grid(Color.LightGray, steps = 6),
//            xAxis = LinePlot.XAxis(stepSize = 1.dp)
        ),


        modifier = Modifier
            .padding(
                top = MaterialTheme.dimens.largePadding,
                end = MaterialTheme.dimens.mediumPadding
            )
            .fillMaxWidth()
            .height(400.dp)
    )
}*/

@Composable
fun StraightLinechart(pointsData: List<Point>) {
    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .steps(pointsData.size - 1)
        .labelData { i -> (1 + i).toString() }
        .axisLabelAngle(20f)
        .labelAndAxisLinePadding(15.dp)
        .axisLabelColor(Color.Blue)
        .axisLineColor(Color.DarkGray)
        .typeFace(Typeface.DEFAULT_BOLD)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(5)
        .labelData { i -> "${(i * 20)}k" }
        .labelAndAxisLinePadding(30.dp)
        .axisLabelColor(Color.Blue)
        .axisLineColor(Color.DarkGray)
        .typeFace(Typeface.DEFAULT_BOLD)
        .build()
    val data = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    lineStyle = LineStyle(
                        lineType = LineType.Straight(),
                        color = Color.Blue,
                        width = 0.6f
                        ),
                    intersectionPoint = IntersectionPoint(color = Color.Red, radius = 3.dp),
                    selectionHighlightPopUp = SelectionHighlightPopUp(popUpLabel = { x, y ->
                        val xLabel = "x : ${(1900 + x).toInt()} "
                        val yLabel = "y : ${String.format("%.2f", y)}"
                        "$xLabel $yLabel"
                    })
                )
            )
        ),

        xAxisData = xAxisData,
        yAxisData = yAxisData,
        backgroundColor = MaterialTheme.colorScheme.surface,
        gridLines = GridLines(color = Color.LightGray)
    )
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        lineChartData = data
    )
}
