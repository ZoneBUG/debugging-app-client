package com.zonebug.debugging.activity.community.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zonebug.debugging.App
import com.zonebug.debugging.DTO.community.CommunityMainDTO
import com.zonebug.debugging.activity.community.list.CommunityListActivity
import com.zonebug.debugging.activity.login.LoginActivity
import com.zonebug.debugging.databinding.ActivityCommunityBinding
import com.zonebug.debugging.retrofit.RetrofitRepository

class CommunityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityBinding
    private lateinit var viewModel: CommunityViewModel

    override fun onRestart() {
        super.onRestart()
        fetchLists()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 리스트들 불러오기
        fetchLists()


        binding.CommunityMoreIssue.setOnClickListener {
            intent = Intent(this, CommunityListActivity::class.java)
            intent.putExtra("tag", "issue")
            startActivity(intent)
        }

        binding.CommunityMoreAsk.setOnClickListener {
            intent = Intent(this, CommunityListActivity::class.java)
            intent.putExtra("tag", "ask")
            startActivity(intent)
        }

        binding.CommunityMoreShare.setOnClickListener {
            intent = Intent(this, CommunityListActivity::class.java)
            intent.putExtra("tag", "share")
            startActivity(intent)
        }
    }

    private fun fetchLists() {
        val repository = RetrofitRepository
        val viewModelFactory = CommunityViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CommunityViewModel::class.java)
        viewModel.getCommunityMain()
        viewModel.myResponse.observe(this, Observer {
            when {
                it.isSuccessful -> {
                    val communityMainDTO = it.body()!!
                    setAdapter("issue", communityMainDTO.issueList)
                    setAdapter("ask", communityMainDTO.askList)
                    setAdapter("share", communityMainDTO.shareList)

                }
                it.code() ==  401 -> {
                    App.prefs.setString("accessToken", "")
                    App.prefs.setString("refreshToken", "")
                    intent = Intent(this@CommunityActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                else -> {
                    Log.d("TAG", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + it.isSuccessful)

                }
            }
        })
    }

    private fun setAdapter(tag: String, postList: List<CommunityMainDTO.MainPost>) {
        if(tag == "issue") {
            binding.CommunityRVIssue.adapter = CommunityRecyclerAdapter(postList)
            binding.CommunityRVIssue.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.CommunityRVIssue.setHasFixedSize(true)
        }

        else if(tag == "ask") {
            binding.CommunityRVAsk.adapter = CommunityRecyclerAdapter(postList)
            binding.CommunityRVAsk.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.CommunityRVAsk.setHasFixedSize(true)
        }

        else if(tag == "share") {
            binding.CommunityRVShare.adapter = CommunityRecyclerAdapter(postList)
            binding.CommunityRVShare.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.CommunityRVShare.setHasFixedSize(true)
        }
    }
}