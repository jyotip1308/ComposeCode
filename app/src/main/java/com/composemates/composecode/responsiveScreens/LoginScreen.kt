package com.composemates.composecode.responsiveScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composemates.composecode.R
import com.composemates.composecode.ui.theme.dimens

@Composable
fun LoginScreen() {
    Surface {
        Column(modifier = Modifier.fillMaxSize())
        {
            TopSection()

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium2))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            )  {

                var mob by remember { mutableStateOf("") }
                MobileTextField(
                    text = mob,
                    label = "Mobile",
                    onTextChange = {mob = it},
                    color = Color.DarkGray,
                    maxLength = 12,
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.medium1))

                CreateAccountSection()
            }
        }
    }
}

@Composable
fun TopSection(){
    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Box(contentAlignment = Alignment.TopCenter){
        Image(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
            painter = painterResource(id = R.drawable.mountain),
            contentDescription = null ,
            contentScale = ContentScale.FillBounds
        )

        Row (modifier = Modifier.padding(top = MaterialTheme.dimens.large),
            verticalAlignment = Alignment.CenterVertically)
        {
            Column {
                Text(text = "The To-let",
                  style = MaterialTheme.typography.headlineMedium,
                  color = uiColor
                )
                Text(text = "Find Your House",
                  style = MaterialTheme.typography.titleMedium,
                  color = uiColor
                )
            }
        }

        Text(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(alignment = Alignment.BottomCenter),
            text = "Login",
            style = MaterialTheme.typography.headlineLarge,
            color = uiColor
        )
    }
}

@Composable
fun CreateAccountSection(){
    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Box (modifier = Modifier
        .fillMaxHeight(0.8f)
        .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ){
        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color(0xFF94A3B8),
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = FontWeight.Normal
                )
            ){
                append("Don't have account")
            }

            withStyle(
                style = SpanStyle(
                    color = uiColor,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = FontWeight.Medium
                )
            ){
                append(" ")
                append("Create now")
            }
        })
    }
}
