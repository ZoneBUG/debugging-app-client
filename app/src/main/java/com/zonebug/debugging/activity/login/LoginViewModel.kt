package com.zonebug.debugging.activity.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zonebug.debugging.DTO.user.LoginRequestDTO
import com.zonebug.debugging.DTO.user.TokenResponseDTO
import com.zonebug.debugging.retrofit.RetrofitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val myResponse : MutableLiveData<Response<TokenResponseDTO>> = MutableLiveData()
//    val myResponse2 : MutableLiveData<Response<SocialLoginResponseDTO>> = MutableLiveData()

    fun signIn(loginRequestDTO: LoginRequestDTO) {
        viewModelScope.launch {
            val response = repository.signIn(loginRequestDTO)
            myResponse.value = response
        }
    }

//    fun signInNaver() {
//        viewModelScope.launch {
//            val response = repository.signInNaver()
//            myResponse2.value = response
//        }
//    }
}