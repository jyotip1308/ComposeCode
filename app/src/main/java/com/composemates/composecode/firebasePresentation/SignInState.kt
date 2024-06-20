package com.composemates.composecode.firebasePresentation

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
