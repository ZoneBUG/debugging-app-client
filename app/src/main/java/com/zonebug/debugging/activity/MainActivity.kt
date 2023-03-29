package com.zonebug.debugging.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zonebug.debugging.App
import com.zonebug.debugging.activity.aboutus.AboutUsActivity
import com.zonebug.debugging.activity.community.main.CommunityActivity
import com.zonebug.debugging.activity.login.LoginActivity
import com.zonebug.debugging.activity.mypage.MyPageActivity
import com.zonebug.debugging.activity.report.ReportActivity
import com.zonebug.debugging.activity.search.SearchActivity
import com.zonebug.debugging.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val transaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val accessToken = App.prefs.getString("accessToken", "")
        Log.d("YMC", "+++++++++++++++++++++++++++++++++++ " + accessToken)

        // My Page
        binding.MainImgBtnMyPage.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Report
        binding.MainBtnReport.setOnClickListener {
            intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }

        // Search
        binding.MainBtnSearch.setOnClickListener {
            intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        // Community
        binding.MainBtnCommunity.setOnClickListener {
            intent = Intent(this, CommunityActivity::class.java)
            startActivity(intent);
        }

        // About Us
        binding.MainBtnAboutUs.setOnClickListener {
            intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }

    }
}