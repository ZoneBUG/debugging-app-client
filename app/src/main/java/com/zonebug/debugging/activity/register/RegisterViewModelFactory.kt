package com.zonebug.debugging.activity.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zonebug.debugging.retrofit.RetrofitRepository

class RegisterViewModelFactory(private val repository : RetrofitRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(repository) as T
    }

}