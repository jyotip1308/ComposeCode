package com.composemates.composecode.websocket

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val message by remember { mutableStateOf("Waiting for messages...") }
    val socketManager = remember { SocketManager() }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            Log.d("MY SOCKET","ONCONNECT")

            socketManager.connect()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Socket.IO Example") })
        }
    ) {it ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = message)
        }
    }
}


