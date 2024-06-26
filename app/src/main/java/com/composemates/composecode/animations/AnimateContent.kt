package com.composemates.composecode.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Animation(){

                val isVisible by remember {
                    mutableStateOf(false)
                }

                    AnimatedContent(
                      targetState = isVisible,
                      modifier = Modifier
                          .fillMaxWidth(1f)
                          .padding(8.dp),
                      label = "Animate Content",
                      transitionSpec = {
                          slideInHorizontally(
                              initialOffsetX = {
                                  if (isVisible) it else -it
                              }
                          ) with slideOutHorizontally(
                              targetOffsetX = {
                                  if (isVisible) -it else it
                              }
                          )
                      }
                  ) {isVisible ->
                      if (isVisible){

                          Box(modifier = Modifier.background(Color.Green))

                      }
                      else {

                          Box(modifier = Modifier.background(Color.Magenta))
                      }
                  }

}



/*
// Horizontal translation animation
val screenWidth = LocalConfiguration.current.screenWidthDp.dp
val offsetX by infiniteTransition.animateFloat(
    initialValue = screenWidth.value,
    targetValue = -screenWidth.value,
    label = "Moving Moon",
    animationSpec = infiniteRepeatable(
        animation = tween(durationMillis = 35000, easing = LinearEasing),
        repeatMode = RepeatMode.Reverse
    )
)*/
