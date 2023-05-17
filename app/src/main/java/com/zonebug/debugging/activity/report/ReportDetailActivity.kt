package com.zonebug.debugging.activity.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zonebug.debugging.databinding.ActivityReportDetailBinding

class ReportDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}