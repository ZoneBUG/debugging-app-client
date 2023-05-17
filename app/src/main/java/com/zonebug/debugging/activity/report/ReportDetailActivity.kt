package com.zonebug.debugging.activity.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.zonebug.debugging.App
import com.zonebug.debugging.DTO.report.ReportResponseDTO
import com.zonebug.debugging.activity.login.LoginActivity
import com.zonebug.debugging.activity.report.adapter.ReportListViewPagerAdapter
import com.zonebug.debugging.databinding.ActivityReportDetailBinding
import com.zonebug.debugging.retrofit.web.RetrofitRepository

class ReportDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportDetailBinding
    private lateinit var viewModel: ReportListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val period = intent.getIntExtra("period", 1)

        getReportList(period)

    }

    private fun getReportList(period: Int) {
        val repository = RetrofitRepository
        val viewModelFactory = ReportListViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[ReportListViewModel::class.java]
        viewModel.getReportList(period)
        viewModel.myResponse.observe(this, Observer {
            when {
                it.isSuccessful -> {
                    val reportResponseDTO : ReportResponseDTO = it.body()!!
                    makeReport(reportResponseDTO)
                }
                it.code() ==  401 -> {
                    App.prefs.setString("accessToken", "")
                    App.prefs.setString("refreshToken", "")
                    intent = Intent(this@ReportDetailActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                else -> {
                    Log.d("TAG", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + it.isSuccessful)

                }
            }
        })
    }

    private fun makeReport(reportResponseDTO : ReportResponseDTO) {

        if (reportResponseDTO.reportItemDTO.isNotEmpty()) {
            binding.ReportDetailViewPager2.adapter = ReportListViewPagerAdapter(reportResponseDTO.reportItemDTO)
            binding.ReportDetailViewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding.wormDotsIndicator.attachTo(binding.ReportDetailViewPager2)

        } else {
            binding.ReportDetailViewPager2.visibility = View.GONE
            binding.wormDotsIndicator.visibility = View.GONE
            binding.ReportDetailTextNo.visibility = View.VISIBLE
        }
    }
}