package com.zonebug.debugging.activity.community.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zonebug.debugging.DTO.community.CommunityDetailCommentDTO
import com.zonebug.debugging.DTO.community.CommunityDetailCommentResponseDTO
import com.zonebug.debugging.DTO.community.CommunityDetailDTO
import com.zonebug.debugging.retrofit.RetrofitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CommunityDetailViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val myResponse : MutableLiveData<Response<CommunityDetailDTO>> = MutableLiveData()
    val myResponse2 : MutableLiveData<Response<CommunityDetailCommentResponseDTO>> = MutableLiveData()

    fun getDetailPost(postId : Long) {
        viewModelScope.launch {
            val response = repository.getPost(postId)
            myResponse.value = response
        }
    }

    fun writePost(communityDetailCommentDTO: CommunityDetailCommentDTO) {
        viewModelScope.launch {
            val response = repository.writeComment(communityDetailCommentDTO)
            myResponse2.value = response
        }
    }
}