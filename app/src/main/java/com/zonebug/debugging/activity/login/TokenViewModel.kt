package com.zonebug.debugging.activity.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zonebug.debugging.DTO.user.LoginRequestDTO
import com.zonebug.debugging.DTO.user.TokenResponseDTO
import com.zonebug.debugging.retrofit.RetrofitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class TokenViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val myResponse : MutableLiveData<Response<TokenResponseDTO>> = MutableLiveData()

    fun signIn(loginRequestDTO: LoginRequestDTO) {
        viewModelScope.launch {
            val response = repository.signIn(loginRequestDTO)
            myResponse.value = response
        }
    }
}