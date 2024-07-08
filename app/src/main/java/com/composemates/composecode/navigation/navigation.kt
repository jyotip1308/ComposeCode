package com.composemates.composecode.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.composemates.composecode.screens.ABC
import com.composemates.composecode.screens.ABCD


@Composable
fun Navigate() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "ABC",
    )

    {
        composable("ABC") {
            ABC(navController)
        }

        composable("ABCD"){
            ABCD()
        }
    }
}


