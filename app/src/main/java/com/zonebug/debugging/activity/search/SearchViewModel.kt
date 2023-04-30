package com.zonebug.debugging.activity.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zonebug.debugging.DTO.search.SearchResponseDTO
import com.zonebug.debugging.retrofit.model.ModelRetrofitRepository
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Response

class SearchViewModel(private val repository: ModelRetrofitRepository) : ViewModel() {

    val myResponse : MutableLiveData<Response<SearchResponseDTO>> = MutableLiveData()
    var name : MutableLiveData<String> = MutableLiveData()

    fun search(image : MultipartBody.Part, model : String) {
        viewModelScope.launch {
            val response = repository.search(image, model)
            myResponse.value = response
        }
    }
}