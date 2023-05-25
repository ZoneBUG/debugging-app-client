package com.zonebug.debugging.activity.community.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zonebug.debugging.App
import com.zonebug.debugging.DTO.community.CommunityDetailCommentDTO
import com.zonebug.debugging.activity.login.LoginActivity
import com.zonebug.debugging.databinding.ActivityCommunityDetailBinding
import com.zonebug.debugging.retrofit.web.RetrofitRepository
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class CommunityDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityDetailBinding
    private lateinit var viewModel: CommunityDetailViewModel

    override fun onRestart() {
        super.onRestart()
        val postId = intent.getLongExtra("postId", 0)
        binding.CommunityDetailCommentInput.text = null
        fetchDetail(postId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postId = intent.getLongExtra("postId", 0)
        fetchDetail(postId)

        binding.CommunityDetailCommentBtn.setOnClickListener {
            writeComment(postId, 0)
        }

    }

    private fun fetchDetail(postId : Long) {
        val repository = RetrofitRepository
        val viewModelFactory = CommunityDetailViewModelFactory(repository)
        val dateFormat = "yyyy-MM-dd HH:mm"

        viewModel = ViewModelProvider(this, viewModelFactory).get(CommunityDetailViewModel::class.java)
        viewModel.getDetailPost(postId)
        viewModel.myResponse.observe(this, Observer {
            when {
                it.isSuccessful -> {

                    val communityDetailDTO = it.body()!!
                    binding.CommunityDetailTitle.text = communityDetailDTO.post.title
                    binding.CommunityDetailContents.text = communityDetailDTO.post.contents
                    binding.CommunityDetailInfoNickname.text = communityDetailDTO.post.nickname
                    binding.CommunityDetailInfoDate.text = SimpleDateFormat(dateFormat).format(communityDetailDTO.post.createdAt)
                    binding.CommunityDetailInfoHits.text = "조회수 (" + communityDetailDTO.post.hits.toString() + ")"
                    binding.CommunityDetailInfoComments.text = "댓글 (" + communityDetailDTO.commentList.size.toString() + ")"


//                    // 수정하기 버튼 visibility
//                    if(!communityDetailDTO.post.isMine) {
//                        binding.CommunityDetailEdit.visibility = View.INVISIBLE
//                    } else {
//                        binding.CommunityDetailEdit.visibility = View.VISIBLE
//                    }

                    // 댓글 유무 알림 텍스트 visibility
                    if(communityDetailDTO.commentList.isEmpty()) {
                        binding.CommunityDetailTextNoComment.visibility = View.VISIBLE
                        binding.CommunityDetailRVComment.visibility = View.GONE
                    } else {
                        binding.CommunityDetailTextNoComment.visibility = View.GONE
                        binding.CommunityDetailRVComment.visibility = View.VISIBLE

                        var parentCommentList = communityDetailDTO.commentList.filter {
                            it.parentId == 0L
                        }

                        var finalCommentList = mutableListOf<CommentDetail>()

                        for(parent in parentCommentList) {
                            var childCommentList = communityDetailDTO.commentList.filter {
                                it.parentId == parent.commentId
                            }
                            finalCommentList.add(CommentDetail(postId, parent, childCommentList))
                        }

                        setAdapter(finalCommentList)
                    }

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

    private fun setAdapter(commentList: MutableList<CommentDetail>) {
        binding.CommunityDetailRVComment.adapter = CommunityDetailRecyclerAdapter(commentList, this, view = binding.root)
        binding.CommunityDetailRVComment.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.CommunityDetailRVComment.setHasFixedSize(true)
    }

    fun writeComment(postId: Long, parentId: Long) {
        var contents = binding.CommunityDetailCommentInput.text.toString()
        val communityDetailCommentDTO = CommunityDetailCommentDTO(postId = postId, parentId = parentId, contents = contents)

        if(contents.trim().toString().isBlank()) {
            Toast.makeText(this@CommunityDetailActivity, "댓글을 입력해주세요", Toast.LENGTH_SHORT).show()

        } else {
            val repository = RetrofitRepository
            val viewModelFactory = CommunityDetailViewModelFactory(repository)

            viewModel = ViewModelProvider(this, viewModelFactory).get(CommunityDetailViewModel::class.java)
            viewModel.writePost(communityDetailCommentDTO)
            viewModel.myResponse2.observe(this, Observer {
                when {
                    it.isSuccessful -> {
                        val communityDetailCommentResponseDTO = it.body()!!
                        onRestart()

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

}