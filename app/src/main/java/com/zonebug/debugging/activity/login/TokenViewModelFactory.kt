package com.zonebug.debugging.activity.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zonebug.debugging.retrofit.RetrofitRepository

class TokenViewModelFactory(private val repository : RetrofitRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TokenViewModel(repository) as T
    }

}