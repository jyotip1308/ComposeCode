package com.composemates.composecode.navigation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.composemates.composecode.firebasePresentation.GoogleAuthUiClient
import com.composemates.composecode.firebasePresentation.GoogleAuthUiClientHilt
import com.composemates.composecode.firebasePresentation.ProfileScreen
import com.composemates.composecode.firebasePresentation.SignInScreen
import com.composemates.composecode.viewModels.FirebaseViewModel
import kotlinx.coroutines.launch

@Composable
fun FirebaseNavGraph(
    navController: NavHostController,
    googleAuthClientHilt: GoogleAuthUiClientHilt,
    applicationContext: Context
) {
    NavHost(
        navController = navController,
        startDestination = "sign_in",
    ) {
        composable("sign_in") {
            val viewModel: FirebaseViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()

            var userSignedIn by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = Unit) {
                userSignedIn = googleAuthClientHilt.getSignedInUser() != null
                if (userSignedIn) {
                    navController.navigate("profile") {
                        popUpTo("sign_in") { inclusive = true }
                    }
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        viewModel.viewModelScope.launch {
                            val signInResult = googleAuthClientHilt.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate("profile")
                    viewModel.resetState()
                }
            }

            SignInScreen(state = state,
                onSignInClick = {
                    viewModel.viewModelScope.launch {
                        val signInIntentSender = googleAuthClientHilt.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )
        }

        composable("profile") {
            val viewModel: FirebaseViewModel = hiltViewModel()

            ProfileScreen(
                userData = googleAuthClientHilt.getSignedInUser(),
                onSignOut = {
                    viewModel.viewModelScope.launch {
                        googleAuthClientHilt.signOut()
                        Toast.makeText(
                            applicationContext,
                            "Signed out",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.navigate("sign_in") {
                            popUpTo("profile") { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}