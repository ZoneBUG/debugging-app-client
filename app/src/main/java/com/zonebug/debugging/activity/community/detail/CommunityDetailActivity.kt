package com.zonebug.debugging.activity.community.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zonebug.debugging.App
import com.zonebug.debugging.activity.login.LoginActivity
import com.zonebug.debugging.databinding.ActivityCommunityDetailBinding
import com.zonebug.debugging.retrofit.RetrofitRepository

class CommunityDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityDetailBinding
    private lateinit var viewModel: CommunityDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postId = intent.getLongExtra("postId", 0)

        val repository = RetrofitRepository
        val viewModelFactory = CommunityDetailViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CommunityDetailViewModel::class.java)
        viewModel.getDetailPost(postId)
        viewModel.myResponse.observe(this, Observer {
            when {
                it.isSuccessful -> {
                    val communityDetailDTO = it.body()!!
                    binding.CommunityDetailTitle.text = communityDetailDTO.post.title
                    binding.CommunityDetailContents.text = communityDetailDTO.post.contents
                    binding.CommunityDetailInfoNickname.text = "닉네임1"
        //                binding.CommunityDetailInfoNickname.text = communityDetailDTO.post.nickname
                    binding.CommunityDetailInfoDate.text = communityDetailDTO.post.createdAt.toString().substring(4, 10)
                    binding.CommunityDetailInfoHits.text = "조회수 (" + communityDetailDTO.post.hits.toString() + ")"
                    binding.CommunityDetailInfoComments.text = "댓글 (" + communityDetailDTO.commentList.size.toString() + ")"

                }
                it.code() ==  401 -> {
                    App.prefs.setString("accessToken", "")
                    App.prefs.setString("refreshToken", "")
                    intent = Intent(this@CommunityDetailActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                else -> {
                    Log.d("TAG", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + it.isSuccessful)

                }
            }

        })
    }

}