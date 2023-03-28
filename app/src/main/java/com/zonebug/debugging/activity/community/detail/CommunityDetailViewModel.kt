package com.zonebug.debugging.activity.community.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zonebug.debugging.DTO.community.CommunityDetailDTO
import com.zonebug.debugging.retrofit.RetrofitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CommunityDetailViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val myResponse : MutableLiveData<Response<CommunityDetailDTO>> = MutableLiveData()

    fun getDetailPost(postId : Long) {
        viewModelScope.launch {
            val response = repository.getPost(postId)
            myResponse.value = response
        }
    }
}