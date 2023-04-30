package com.zonebug.debugging.activity.community.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zonebug.debugging.DTO.community.CommunityMainDTO
import com.zonebug.debugging.retrofit.web.RetrofitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CommunityViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val myResponse : MutableLiveData<Response<CommunityMainDTO>> = MutableLiveData()

    fun getCommunityMain() {
        viewModelScope.launch {
            val response = repository.getCommunityMain()
            myResponse.value = response
        }
    }
}