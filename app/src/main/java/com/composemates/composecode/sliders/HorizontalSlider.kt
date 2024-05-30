package com.composemates.composecode.sliders

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun Sliderr(){

    val sliderPositionState = remember {
        mutableFloatStateOf(0f)
    }

    Slider(value = sliderPositionState.value,
        onValueChange = {newVal ->
            sliderPositionState.value = newVal

        },
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        steps = 5)
}