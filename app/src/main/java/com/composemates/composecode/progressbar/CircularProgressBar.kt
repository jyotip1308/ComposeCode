package com.composemates.composecode.progressbar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.times
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.delay

@Composable
fun ProgressBarCircular(){

    val showProgressBar = remember { mutableStateOf(true) }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize())
    {
       if (showProgressBar.value){

           CircularProgressBar(percentage = 1f ,
               duration = 20,
               onTimeEnd = {
                   showProgressBar.value = false
               }
           )
       }
    }
}


 // TO updated time and circular bar every second (Not Right)

/*
@Composable
fun CircularProgressBar(
    percentage: Float,
    duration: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Blue,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 10000,
    animDelay: Int = 0
) {
    var animationPlayed by remember { mutableStateOf(false) }
    var curPercentage by remember { mutableStateOf(0f) }
    var remainingTime by remember { mutableStateOf(duration) }

    LaunchedEffect(key1 = true) {
        animationPlayed = true
        val interval = animDuration / duration

        for (i in 0 until duration) {
            delay(interval.toLong())
            curPercentage = (i + 1) / duration.toFloat()
            remainingTime = duration - (i + 1)
        }
    }

    val animatedPercentage by animateFloatAsState(
        targetValue = curPercentage,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        ),
        label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * animatedPercentage,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = "${remainingTime}s",
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}
*/

// Not to updated time and circular bar every second

/*
@Composable
fun CircularProgressBar(
    percentage: Float,
    duration: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Blue,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 10000,
    animDelay: Int = 0
) {
    var animationPlayed by remember { mutableStateOf(false) }

    val curPercentage by animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        ), label = ""
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * curPercentage,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        // Calculate the remaining time
        val remainingTime = ((1f - curPercentage) * duration).toInt()

        Text(
            text = "${remainingTime}s",
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}
*/


// TO updated only time every second

/*
@Composable
fun CircularProgressBar(
    percentage: Float,
    duration: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Blue,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 10000,
    animDelay: Int = 0
) {
    var remainingTime by remember { mutableStateOf(duration) }

    LaunchedEffect(key1 = true) {
        for (i in duration downTo 1) {
            delay(1000L)
            remainingTime = i - 1
        }
    }

    val animatedPercentage by animateFloatAsState(
        targetValue = if (remainingTime > 0) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        ),
        label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * animatedPercentage,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = "${remainingTime}s",
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}
*/


// TO updated time and circular bar every second (Perfect and Right)
@Composable
fun CircularProgressBar(
    percentage: Float,
    duration: Int,
    fontSize: TextUnit = 16.sp,
    radius: Dp = 40.dp,
    color: Color = Color.Blue,
    strokeWidth: Dp = 3.dp,
    animDuration: Int = 10000,
    animDelay: Int = 0,
    onTimeEnd: () -> Unit
) {
    // State variable to keep track of remaining time
    var remainingTime by remember { mutableIntStateOf(duration) }

    // State variable to keep track of progress
    var progress by remember { mutableFloatStateOf(0f) }

    // Effect to start the countdown timer and update the progress
    LaunchedEffect(key1 = true) {
        // Calculate the duration of each step (not used in the code)
        val stepDuration = animDuration / duration
        // Loop from duration down to 0
        for (i in duration downTo 0) {
            // Wait for 1 second
            delay(1000L)
            // Update the remaining time
            remainingTime = i
            // Update the progress based on elapsed time
            progress = (duration - i) / duration.toFloat()
        }
        onTimeEnd()
    }

    // Box to center the content
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 1f) // Set the size of the box to the diameter of the circle
    ) {
        // Canvas to draw the circular progress bar
        Canvas(modifier = Modifier.size(radius * 1f)) {
            // Draw the arc for the progress bar
            drawArc(
                color = color,
                startAngle = -90f, // Start at the top (12 o'clock position)
                sweepAngle = 360 * progress, // Sweep angle based on progress
                useCenter = false, // Do not draw a pie slice
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round) // Set the stroke style
            )
        }

        // Text to display the remaining time
        Text(
            text = "${remainingTime}s", // Display remaining time followed by "s"
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}