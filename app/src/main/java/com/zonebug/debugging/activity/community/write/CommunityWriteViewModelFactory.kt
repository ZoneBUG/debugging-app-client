package com.zonebug.debugging.activity.community.write

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zonebug.debugging.retrofit.RetrofitRepository

class CommunityWriteViewModelFactory(private val repository : RetrofitRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommunityWriteViewModel(repository) as T
    }

}