package com.zonebug.debugging.activity.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zonebug.debugging.DTO.user.RegisterRequestDTO
import com.zonebug.debugging.DTO.user.RegisterResponseDTO
import com.zonebug.debugging.retrofit.web.RetrofitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val myResponse : MutableLiveData<Response<RegisterResponseDTO>> = MutableLiveData()

    fun signUp(registerRequestDTO: RegisterRequestDTO) {
        viewModelScope.launch {
            val response = repository.signUp(registerRequestDTO)
            myResponse.value = response
        }
    }
}