package com.composemates.composecode

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.composemates.composecode.firebasePresentation.GoogleAuthUiClient
import com.composemates.composecode.firebasePresentation.GoogleAuthUiClientHilt
import com.composemates.composecode.firebasePresentation.ProfileScreen
import com.composemates.composecode.firebasePresentation.SignInScreen
import com.composemates.composecode.kotlinFlows.CountDownUi
import com.composemates.composecode.navigation.FirebaseNavGraph
import com.composemates.composecode.networkMonitor.NetworkConnectivityHelper
import com.composemates.composecode.responsiveScreens.LoginScreen
import com.composemates.composecode.ui.theme.ComposeCodeTheme
import com.composemates.composecode.viewModels.AuthViewModel
import com.composemates.composecode.viewModels.FirebaseViewModel
import com.composemates.composecode.viewModels.viewodel.FlowViewModel
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //Firebase using hilt
 /*   private val googleAuthClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }*/

    @Inject
    lateinit var googleAuthUiClientHilt: GoogleAuthUiClientHilt

    @Inject
    lateinit var networkConnectivityHelper: NetworkConnectivityHelper
    private val authViewModel: AuthViewModel by viewModels()
    private val viewModel: FlowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkConnectivityHelper.registerNetworkCallback()

        setContent {
            ComposeCodeTheme {

                // Firebase using hilt navigation code

                /*val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "sign_in",
                )

                {
                    composable("sign_in") {
                        val viewModel = viewModel<FirebaseViewModel>()
                        val state by viewModel.state.collectAsState()

                        LaunchedEffect(key1 = Unit){
                            if (googleAuthClient.getSignedInUser() != null){
                                navController.navigate("profile")
                            }
                        }

                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthClient.signInWithIntent(
                                            intent = result.data?: return@launch
                                        )
                                       viewModel.onSignInResult(signInResult)
                                    }
                                }

                            }
                        )

                        LaunchedEffect(key1 = state.isSignInSuccessful){
                            if(state.isSignInSuccessful){
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
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            }
                        )
                    }

                    composable("profile"){
                        ProfileScreen(
                            userData = googleAuthClient.getSignedInUser(),
                            onSignOut = {
                                lifecycleScope.launch {
                                    googleAuthClient.signOut()
                                    Toast.makeText(
                                        applicationContext,
                                        "Signed out",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.popBackStack()
                                }
                            }
                        )
                    }
                }*/

                LoginScreen()

              /*  val navController = rememberNavController()
                FirebaseNavGraph(navController = navController,
                    googleAuthClientHilt = googleAuthUiClientHilt,
                    applicationContext = applicationContext)*/

//                CountDownUi(viewModel)
//                CurrentLocation()
//                Navigate()
//                Login(authViewModel)
//              SwipeText()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        networkConnectivityHelper.unregisterNetworkCallback()
    }
}
