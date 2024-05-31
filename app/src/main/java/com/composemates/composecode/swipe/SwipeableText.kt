package com.composemates.composecode.swipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun SwipeText(){

    val thoughts = listOf(
        "The best way to predict the future is to create it.",
        "Just one small positive thought in the morning can change your whole day.",
        "Opportunities don't happen, you create them.",
        "It is never too late to be what you might have been."
    )

            val pagerState = rememberPagerState{ thoughts.size }
            val scope = rememberCoroutineScope()
            Box(modifier = Modifier.fillMaxSize()){
                HorizontalPager(
//                        pageCount = animals.size,
                    state = pagerState,
                    key = {thoughts[it]},
                    pageSize = PageSize.Fill

                ) { index->

                    Boxes(text = thoughts[index])


                }

                Box(modifier = Modifier
                    .offset(y = -(16).dp)
                    .fillMaxWidth(0.3f)
                    .clip(RoundedCornerShape(100))
                    .background(Color.White)
                    .padding(8.dp)
                    .align(Alignment.BottomCenter)
                ){
                    IconButton(
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage - 1
                                )
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterStart)) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Go back"
                        )
                    }
                    IconButton(onClick =
                    {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage + 1
                            )
                        }
                    },
                        modifier = Modifier.align(Alignment.CenterEnd)) {
                        Icon(imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Go forward"
                        )
                    }
                }
            }
    }

@Composable
fun Boxes(text: String){
    Box(modifier = Modifier.padding(36.dp))
    {
        Text(text = text,
            fontSize = 24.sp
        )
    }
}