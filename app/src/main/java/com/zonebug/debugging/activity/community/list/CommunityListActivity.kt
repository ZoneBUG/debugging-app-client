package com.zonebug.debugging.activity.community.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zonebug.debugging.App
import com.zonebug.debugging.DTO.community.CommunityListDTO
import com.zonebug.debugging.activity.community.write.CommunityWriteActivity
import com.zonebug.debugging.activity.login.LoginActivity
import com.zonebug.debugging.databinding.ActivityCommunityListBinding
import com.zonebug.debugging.retrofit.RetrofitRepository

class CommunityListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommunityListBinding
    private lateinit var viewModel: CommunityListViewModel

    override fun onRestart() {
        super.onRestart()
        val tag = intent.getStringExtra("tag")
        fetchList(tag!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tag = intent.getStringExtra("tag")
        var tag_title = ""
        when {
            tag!! == "issue" -> {
                tag_title = "오늘의 Issue"
                binding.CommunityListButton.visibility = View.GONE
            }
            tag!! == "ask" -> tag_title = "궁금해요!"
            tag!! == "share" -> tag_title = "공유해요!"
        }
        binding.ToolbarCommunityListText.text = tag_title


        // 리스트 불러오기
        fetchList(tag!!)


        // 글 작성하기
        binding.CommunityListButton.setOnClickListener {
            intent = Intent(this@CommunityListActivity, CommunityWriteActivity::class.java)
            intent.putExtra("tag", tag)
            intent.putExtra("tag_title", tag_title)
            startActivity(intent)
        }
    }

    private fun fetchList(tag: String) {
        val repository = RetrofitRepository
        val viewModelFactory = CommunityListViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CommunityListViewModel::class.java)
        viewModel.getCommunityList(tag!!, 0)
        viewModel.myResponse.observe(this, Observer {
            when {
                it.isSuccessful -> {
                    val communityListDTO = it.body()!!
                    setListAdapter(communityListDTO.postList)

                }
                it.code() ==  401 -> {
                    App.prefs.setString("accessToken", "")
                    App.prefs.setString("refreshToken", "")
                    intent = Intent(this@CommunityListActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                else -> {
                    Log.d("TAG", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + it.isSuccessful)

                }
            }
        })
    }

    private fun setListAdapter(postList: List<CommunityListDTO.ListPost>) {
        binding.CommunityListRV.adapter = CommunityListRecyclerAdapter(postList)
        binding.CommunityListRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.CommunityListRV.setHasFixedSize(true)
    }
}