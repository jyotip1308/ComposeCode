package com.composemates.composecode.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.composemates.composecode.R

@Composable
fun DropDownDemo(){
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("Select") }
    val items = listOf("item1" , "item2", "item3")

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .wrapContentSize(Alignment.TopStart)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { expanded = true }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedItem,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(id = if (expanded) R.drawable.baseline_arrow_drop_up_24 else R.drawable.arrowdropdown),
                    contentDescription = null
                )
            }
            
            DropdownMenu(expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {

                items.forEach{label ->
                    DropdownMenuItem(text = { Text(text = label) }, onClick = {
                        selectedItem = label
                        expanded = false }
                    )

                }
            }

        }

    }
}
