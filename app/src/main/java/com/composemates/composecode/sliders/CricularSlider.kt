package com.composemates.composecode.sliders

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CircularSlider(
    modifier: Modifier = Modifier,
    initialValue: Float = 0f,
    onValueChange: (Float) -> Unit,
    steps: Int = 100
) {
    var angle by remember { mutableFloatStateOf(initialValue * 360f) }

    Box(modifier = modifier.size(200.dp)
//        .background(color = Color.White)
        .padding(8.dp)
    ) {
        Canvas(modifier = Modifier
            .size(200.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val center = Offset(size.width / 2f, size.height / 2f)
                    val touchPosition = change.position
                    val angleRad = atan2(
                        touchPosition.y - center.y,
                        touchPosition.x - center.x
                    )
                    val newAngle = (angleRad * (180 / PI)).toFloat() + 180f
                    angle = newAngle
                    onValueChange(angle / 360f)
                }
            }) {
            val radius = size.minDimension / 2
            val center = Offset(size.width / 2, size.height / 2)
            drawCircle(
                color = Color.Red,
                radius = radius,
                center = center,
                style = Stroke(width = 4.dp.toPx())
            )
            val thumbRadius = 10.dp.toPx()
            val angleRad = Math.toRadians(angle.toDouble()).toFloat()
            val thumbX = center.x + radius * cos(angleRad)
            val thumbY = center.y + radius * sin(angleRad)
            drawCircle(
                color = Color.Blue,
                radius = thumbRadius,
                center = Offset(thumbX, thumbY)
            )
        }
    }
}



@Preview
@Composable
fun TestCircularSlider() {
    var value by remember { mutableFloatStateOf(0f) }
    CircularSlider(
        initialValue = value,
        onValueChange = { newValue ->
            value = newValue
        },
        steps = 5
    )
}
