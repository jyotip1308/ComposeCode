package com.composemates.composecode.swipe

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composemates.composecode.R
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Preview
@Composable
fun Swipe(){

    val archive = SwipeAction(
        onSwipe = {
            Log.d("Swipe", "Swipe: Archive")
        },
        icon = {
          Icon(
              modifier = Modifier.padding(16.dp),
              painter = painterResource(id = R.drawable.ic_archive),
              contentDescription = null,
              tint = Color.White
          )
        },
        background = Color.Green
    )

    val email = SwipeAction(
        onSwipe = {
            Log.d("Swipe", "Swipe: Archive")
        },
        icon = {
            Icon(
                modifier = Modifier.padding(16.dp),
                painter = painterResource(id = R.drawable.ic_archive),
                contentDescription = null,
                tint = Color.White
            )
        },
        background = Color.Green
    )

    SwipeableActionsBox (startActions = listOf(archive)){
        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center){

            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(color = Color.Yellow)){

                Text(text = "Swipe this Yellow Box..",
                    fontSize = 30.sp,
                    color = Color.Blue,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

    }


}

