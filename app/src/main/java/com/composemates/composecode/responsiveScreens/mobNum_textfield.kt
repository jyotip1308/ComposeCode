package com.composemates.composecode.responsiveScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//abc
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLine: Int = 1,
    onTextChange: (String) -> Unit,
    color: Color,
    maxLength: Int,
    keyboardType: KeyboardType = KeyboardType.Number,
//    keyboardOptions: KeyboardOptions,
    onImeAction:ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    shape: RoundedCornerShape

) {
    val limitedText = text.take(maxLength)
    val conf = LocalConfiguration.current
    val widthdp = conf.screenWidthDp.dp

    Box(
        contentAlignment = Alignment.CenterStart,

        ) {
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.LightGray,
                containerColor = Color.White,
                focusedLabelColor = Color.Black),
            value = text,
            onValueChange = {
                val newText = it.take(maxLength)
                onTextChange(newText)
            },
            shape= shape,
            maxLines = maxLine,

            keyboardOptions = KeyboardOptions(keyboardType = keyboardType,
                                imeAction = onImeAction),
            keyboardActions = onAction,
            modifier = Modifier.fillMaxWidth(),
//                .width(widthdp/4),
            textStyle = LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                color = color,
                fontSize = MaterialTheme.typography.labelMedium.fontSize
            ),
        )
    }
}