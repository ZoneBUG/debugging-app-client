package com.zonebug.debugging.activity.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zonebug.debugging.retrofit.web.RetrofitRepository

class ReportListViewModelFactory(private val repository : RetrofitRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReportListViewModel(repository) as T
    }

}