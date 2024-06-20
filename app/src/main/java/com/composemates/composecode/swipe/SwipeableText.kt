package com.composemates.composecode.swipe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composemates.composecode.R
import com.composemates.composecode.ui.theme.kanitText
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun SwipeText(){

    val context = LocalContext.current
    val thoughts = listOf(
        context.getString(R.string.thought),
        context.getString(R.string.thought2),
        context.getString(R.string.thought3),
        context.getString(R.string.thought4)

    )

    val header = listOf(
        "benefit 1",
        "benefit 2",
        "benefit 3",
        "benefit 4"

    )
            val pagerState = rememberPagerState{ thoughts.size }
            val scope = rememberCoroutineScope()
            Box(modifier = Modifier.fillMaxSize()){
                HorizontalPager(
                    state = pagerState,
                    key = {thoughts[it]},
                    pageSize = PageSize.Fill

                ) { index->
                    Boxes(headerText = header[index],
                        text = thoughts[index])
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
fun Boxes(headerText: String,text: String){
    Box(modifier = Modifier.padding(36.dp)
        .fillMaxSize(), // Ensure the Box fills the available space
    )
    {
        Column (modifier = Modifier.fillMaxSize()){
            Row {
//                Icon(
//                    painter = painterResource(id = R.drawable.ellipseorange),
//                    contentDescription = "Hindi",
//                    tint = Color.Unspecified
//                )
                Text(text = headerText)
            }

            Spacer(modifier = Modifier.height(36.dp))

            Text(text = text,
                style = kanitText
            )
        }

    }
}