package com.composemates.composecode.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.composemates.composecode.network.Resource
import com.composemates.composecode.repository.AuthRepository
import com.composemates.composecode.response.login_response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository) : ViewModel()

{


    private val _loginResponse: MutableStateFlow<Resource<Response<login_response>>> = MutableStateFlow(Resource.Noaction)

    val loginResponse: MutableStateFlow<Resource<Response<login_response>>>
        get() = _loginResponse

    fun clearLoginResponse(){
        _loginResponse.value = Resource.Noaction
    }
        fun login(
          employee_id: String,
          password: String
      ) = viewModelScope.launch {
          _loginResponse.value = Resource.Loading
          _loginResponse.value = repository.login(employee_id, password)
      }

    }