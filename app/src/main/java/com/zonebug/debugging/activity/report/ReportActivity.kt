package com.zonebug.debugging.activity.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zonebug.debugging.App
import com.zonebug.debugging.DTO.report.ReportResponseDTO
import com.zonebug.debugging.activity.login.LoginActivity
import com.zonebug.debugging.databinding.ActivityReportBinding
import com.zonebug.debugging.retrofit.web.RetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportBinding
    private lateinit var viewModel: ReportListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Create Report
        binding.ReportBtn.setOnClickListener {
            val p = binding.ReportTextInputPeriod.text

            when {
                p == null -> {
                    Toast.makeText(this@ReportActivity, "보고서 생성 주기를 입력해주세요", Toast.LENGTH_SHORT).show()
                }

                p.toString().toInt() in 1..365 -> {
                    intent = Intent(this, ReportDetailActivity::class.java)
                    intent.putExtra("period", p.toString().toInt())
                    startActivity(intent)
                    binding.ReportTextInputPeriod.text = null
                }

                else -> {
                    Toast.makeText(this@ReportActivity, "1 ~ 365 사이의 일수를 입력해주세요", Toast.LENGTH_SHORT).show()
                    binding.ReportTextInputPeriod.text = null
                }
            }


        }

    }
}