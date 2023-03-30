package com.zonebug.debugging.activity.community.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zonebug.debugging.retrofit.RetrofitRepository

class CommunityListViewModelFactory(private val repository : RetrofitRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommunityListViewModel(repository) as T
    }

}