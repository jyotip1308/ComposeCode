package com.composemates.composecode.kotlinFlows

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.composemates.composecode.viewModels.viewodel.FlowViewModel

@Composable
fun CountDownUi(viewModel: FlowViewModel){
    val time = viewModel.countDownFlow.collectAsState(initial = 10)
    Box (modifier = Modifier.fillMaxSize()){
     Text(text = time.value.toString(),
         fontSize = 20.sp,
         modifier = Modifier.align(Alignment.Center))
    }
}