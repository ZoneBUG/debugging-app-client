package com.zonebug.debugging.activity.report

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zonebug.debugging.DTO.report.ReportResponseDTO
import com.zonebug.debugging.retrofit.web.RetrofitRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ReportListViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val myResponse : MutableLiveData<Response<ReportResponseDTO>> = MutableLiveData()

    fun getReportList(period : Int) {
        viewModelScope.launch {
            val response = repository.getReportList(period)
            myResponse.value = response
        }
    }
}