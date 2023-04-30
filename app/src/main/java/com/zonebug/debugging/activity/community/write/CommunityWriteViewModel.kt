package com.zonebug.debugging.activity.community.write

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zonebug.debugging.DTO.community.CommunityWriteRequestDTO
import com.zonebug.debugging.DTO.community.CommunityWriteResponseDTO
import com.zonebug.debugging.retrofit.web.RetrofitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CommunityWriteViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val myResponse : MutableLiveData<Response<CommunityWriteResponseDTO>> = MutableLiveData()

    fun writePost(communityWriteRequestDTO: CommunityWriteRequestDTO) {
        viewModelScope.launch {
            val response = repository.writePost(communityWriteRequestDTO)
            myResponse.value = response
        }
    }
}