package com.zonebug.debugging.activity.community.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zonebug.debugging.DTO.community.CommunityListDTO
import com.zonebug.debugging.retrofit.web.RetrofitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CommunityListViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val myResponse : MutableLiveData<Response<CommunityListDTO>> = MutableLiveData()

    fun getCommunityList(tag: String, pageNum: Long) {
        viewModelScope.launch {
            val response = repository.getCommunityList(tag, pageNum)
            myResponse.value = response
        }
    }
}