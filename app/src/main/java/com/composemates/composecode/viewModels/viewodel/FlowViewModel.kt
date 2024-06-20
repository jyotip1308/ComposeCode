package com.composemates.composecode.viewModels.viewodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlowViewModel @Inject constructor() : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)

        while (currentValue > 0){
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    init {

//        collectFlow()

        flattenFlow()

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun flattenFlow(){
        val flow1 = flow {
            emit(1)
            delay(500L)
            emit(2)
        }

        viewModelScope.launch {
            flow1.flatMapConcat {value ->
                flow {
                    emit(value + 1)
                    delay(500L)
                    emit(value+2)
                }
            }.collect{
                println("The value is $it")
            }
        }
    }



    //Collect
    private fun collectFlow(){
        viewModelScope.launch {
  /*         val count = countDownFlow
                .filter {time ->
                    time % 2 == 0
                }
                .map { time ->
                    time * time

                }
                .onEach { time ->
                    println(time)

                }
               .count {
                   it % 2 == 0
               }

                println("The current time is $count")*/

            val reduceValue = countDownFlow
                .reduce { accumulator, value ->
                    accumulator + value
                }
            println("Reduce Value is $reduceValue" )

            val foldValue = countDownFlow
                .fold(100) { accumulator, value ->
                    accumulator + value
                }
            println("Fold Value is $foldValue" )

        }
    }

//CollectLatest

//    private fun collectFlow(){
//        viewModelScope.launch {
//            countDownFlow.collectLatest { time ->
//                delay(1500L)
//                println("The current time is $time")
//            }
//        }
//    }

}