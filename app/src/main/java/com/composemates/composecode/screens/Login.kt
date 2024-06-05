package com.composemates.composecode.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composemates.composecode.network.Resource
import com.composemates.composecode.viewModels.AuthViewModel


//@Preview
@Composable
fun Login(viewModel: AuthViewModel){

    var employee_id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val loginResponse by viewModel.loginResponse.collectAsState()

    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        OutlinedTextField(
            value = employee_id,
            onValueChange = { newValue ->
                employee_id = newValue },
            label = { Text("Employee Id") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { newValue ->
                password = newValue },
            label = { Text("Password") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            viewModel.login(employee_id, password)
        },
            shape = RoundedCornerShape(9.dp),
            border = BorderStroke(3.dp, Color.Magenta),
            colors = ButtonDefaults.buttonColors(contentColor = Color.Black, containerColor =  Color.Yellow),

            ) {
            Text(
                text = "Login",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

            when (loginResponse) {
                is Resource.Loading -> {
                    // Show loading state if needed
                }
                is Resource.Success -> {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure -> {
                    Toast.makeText(context, "Login Failed: ${(loginResponse as Resource.Failure).errorBody}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Noaction -> {
                    // Do nothing
                }
            }

        }
    }
}