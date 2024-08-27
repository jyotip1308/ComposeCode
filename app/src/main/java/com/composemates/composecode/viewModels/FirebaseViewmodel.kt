package com.composemates.composecode.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composemates.composecode.firebasePresentation.SignInResult
import com.composemates.composecode.firebasePresentation.SignInState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
class FirebaseViewModel: ViewModel() {

    // One Way to use channel

   /* private var channel = Channel<Language> ()

    init {
       viewModelScope.launch {

           Log.d("Language", "Python Sent")
           channel.send(Language.Python)

           Log.d("Language", "Kotlin Sent")
           channel.send(Language.Kotlin)
           channel.send(Language.Java)

           channel.close()
       }

        viewModelScope.launch {

            *//*Log.d("Language", "${channel.isClosedForReceive}")
            Log.d("Language", "${channel.receive()}")
            Log.d("Language", "${channel.receive()}")
            Log.d("Language", "${channel.isClosedForReceive}")*//*

            channel.consumeEach {
                Log.d("Language", it.name)
            }

        }
    }*/


    // Another way to use channels (Better way)

    private var channel : ReceiveChannel<Language> = Channel()

    init {
        viewModelScope.launch {
           channel = produce {
               channel.send(Language.Java)
               channel.send(Language.Kotlin)
               channel.send(Language.Python)
           }
        }

        viewModelScope.launch {

            Log.d("Language", "${channel.isClosedForReceive}")
            channel.consumeEach {
                Log.d("Language", it.name)
            }

            Log.d("Language", "${channel.isClosedForReceive}")


        }
    }

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult){
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}

enum class Language {
    Kotlin,
    Java,
    Python,
    Html
}