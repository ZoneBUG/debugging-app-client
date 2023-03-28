package com.zonebug.debugging.activity.community.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zonebug.debugging.App
import com.zonebug.debugging.DTO.community.CommunityListDTO
import com.zonebug.debugging.activity.login.LoginActivity
import com.zonebug.debugging.databinding.ActivityCommunityListBinding
import com.zonebug.debugging.retrofit.RetrofitObject
import com.zonebug.debugging.retrofit.RetrofitRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommunityListBinding
    private lateinit var viewModel: CommunityListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tag = intent.getStringExtra("tag")
        if(tag!! == "issue") binding.ToolbarCommunityListText.text = "오늘의 Issue"
        else if(tag!! == "ask") binding.ToolbarCommunityListText.text = "궁금해요!"
        else if(tag!! == "share") binding.ToolbarCommunityListText.text = "공유해요!"

        val repository = RetrofitRepository
        val viewModelFactory = CommunityListViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CommunityListViewModel::class.java)
        viewModel.getCommunityList(tag, 0)
        viewModel.myResponse.observe(this, Observer {
            if(it.isSuccessful) {
                val communityListDTO = it.body()!!
                setListAdapter(communityListDTO.postList)

            } else if(it.code() ==  401) {
                App.prefs.setString("accessToken", "")
                App.prefs.setString("refreshToken", "")
                intent = Intent(this@CommunityListActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Log.d("TAG", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + it.isSuccessful)

            }
        })
    }

    private fun setListAdapter(postList: List<CommunityListDTO.ListPost>) {
        binding.CommunityListRV.adapter = CommunityListRecyclerAdapter(postList)
        binding.CommunityListRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.CommunityListRV.setHasFixedSize(true)
    }
}