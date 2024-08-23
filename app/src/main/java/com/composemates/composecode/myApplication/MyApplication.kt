package com.composemates.composecode.myApplication

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseApp.initializeApp(this)
    }

}


/*
  DropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false },
  ) {
      items.forEach { item ->
          val isOkDisabled = item == "OK" && timerCompleted
          DropdownMenuItem(
              {
                  Text(
                      text = item,
                      style = TextStyle(
                          fontSize = MaterialTheme.typography.bodySmall.fontSize,
                          color = pureBlack,
                          textAlign = TextAlign.Center
                      )
                  )
              },

              onClick = {
                  if (!isOkDisabled) {
                      selectedItem = item
                      if (selectedItem == "NG") {
                          progressTimer.startTimer(paramId) {
                              showLogs("Timer finished for paramId:", paramId)
                              myComponents.mainViewModel.notify(
                                  myComponents.mainViewModel.getStationValue(),
                                  paramId,
                                  myComponents.mainViewModel.getStationValue().split(" ").take(2).joinToString(" ")
                              ) { result ->
                                  result.onSuccess { notificationId ->
                                      println("Notification ID ID: $notificationId")
                                      myComponents.mainViewModel.myChecksheetNotificationMap[paramId] = notificationId
                                  }.onFailure { exception ->
                                      println("Notification failed: ${exception.message}")
                                  }
                              }
                          }
                          myComponents.mainViewModel.checkSheetList[index] = item
                      } else if (selectedItem == "OK" && !timerStarted) {
                          progressTimer.stopTimer(paramId)
                          myComponents.mainViewModel.checkSheetList[index] = item
                      }
                      expanded = false
                  }
              }
          )
      }
  }
}
*/



//                        onClick = {
//                            if(!isOkDisabled){
//
//                            }
//                            selectedItem = item
//                            if (selectedItem == "NG") {
//
////                               progressState[paramId] = true
//                               /* progressTimer.startTimer(paramId) {
//                                       showLogs("Timer finished for paramId:", paramId)
//                                }
//
//                                myComponents.mainViewModel.checkSheetList.set(index, item)
//                                myComponents.mainViewModel.checkSheetList.forEach { println(it) }
//                                myComponents.mainViewModel.notify(myComponents.mainViewModel.getStationValue(), paramId,
////                        myComponents.mainViewModel.floorNum
//                                    myComponents.mainViewModel.getStationValue().split(" ").take(2)
//                                        .joinToString(" ")
//                                ) { result ->
//                                    result.onSuccess { notificationId ->
//                                        // Handle success, for example:
//                                        println("Notification ID ID: $notificationId")
//                                        myComponents.mainViewModel.myChecksheetNotificationMap[paramId] =
//                                            notificationId
//                                        println("Notification ID ID: ${myComponents.mainViewModel.mChecksheetData.value}")
//
//                                    }.onFailure { exception ->
//                                        // Handle failure, for example:
//                                        println("Notification failed: ${exception.message}")
//                                        // Update UI or state here
//                                    }
//                                }*/
//
//                                progressTimer.startTimer(paramId) {
//                                    showLogs("Timer finished for paramId:", paramId)
//                                    myComponents.mainViewModel.notify(
//                                        myComponents.mainViewModel.getStationValue(),
//                                        paramId,
//                                        myComponents.mainViewModel.getStationValue().split(" ").take(2).joinToString(" ")
//                                    ) { result ->
//                                        result.onSuccess { notificationId ->
//                                            println("Notification ID ID: $notificationId")
//
//                                            myComponents.mainViewModel.myChecksheetNotificationMap[paramId] = notificationId
//                                        }.onFailure { exception ->
//                                            println("Notification failed: ${exception.message}")
//                                        }
//                                    }
//                                }
//                                myComponents.mainViewModel.checkSheetList.set(index, item)
//                                expanded = false
//
//                            } else if (selectedItem == "OK") {
////                                progressState[paramId] = false
//
//                                progressTimer.stopTimer(paramId)
//                                myComponents.mainViewModel.checkSheetList.set(index, item)
//                                myComponents.mainViewModel.checkSheetList.forEach { println(it) }
//                                expanded = false
//                            }
//                        }
//                    )
//                }
//            }
//        }
