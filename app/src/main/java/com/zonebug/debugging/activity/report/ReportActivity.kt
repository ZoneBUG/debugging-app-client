package com.zonebug.debugging.activity.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zonebug.debugging.databinding.ActivityReportBinding

class ReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("TAG", "----1----------------------------------")


        // Create Report
        binding.ReportBtn.setOnClickListener {
            Log.d("TAG", "------2--------------------------------")
            intent = Intent(this, ReportDetailActivity::class.java)

            startActivity(intent)
            finish()
        }
    }
}