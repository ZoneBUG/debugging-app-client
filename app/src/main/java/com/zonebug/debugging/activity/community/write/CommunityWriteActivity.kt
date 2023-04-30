package com.zonebug.debugging.activity.community.write

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zonebug.debugging.App
import com.zonebug.debugging.DTO.community.CommunityWriteRequestDTO
import com.zonebug.debugging.activity.community.detail.CommunityDetailActivity
import com.zonebug.debugging.activity.login.LoginActivity
import com.zonebug.debugging.databinding.ActivityCommunityWriteBinding
import com.zonebug.debugging.retrofit.web.RetrofitRepository

class CommunityWriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityWriteBinding
    private lateinit var viewModel : CommunityWriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tag = intent.getStringExtra("tag").toString()
        val tag_title = intent.getStringExtra("tag_title").toString()
        binding.ToolbarCommunityWriteText.text = tag_title

        binding.CommunityWriteButtonFin.setOnClickListener {
            val title = binding.CommunityWriteInputTitle.text.toString()
            val contents = binding.CommunityWriteInputContents.text.toString()

            when {
                title.trim().isBlank() -> {
                    Toast.makeText(this@CommunityWriteActivity, "제목을 입력해주세요", Toast.LENGTH_SHORT).show()
                }
                contents.trim().isBlank() -> {
                    Toast.makeText(this@CommunityWriteActivity, "내용을 입력해주세요", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this@CommunityWriteActivity, "ok", Toast.LENGTH_SHORT).show()
                    val communityWriteDTO = CommunityWriteRequestDTO(title = title, contents = contents, tag = tag)

                    val repository = RetrofitRepository
                    val viewModelFactory = CommunityWriteViewModelFactory(repository)

                    viewModel = ViewModelProvider(this, viewModelFactory).get(CommunityWriteViewModel::class.java)
                    viewModel.writePost(communityWriteDTO)
                    viewModel.myResponse.observe(this, Observer {
                        when {
                            it.isSuccessful -> {
                                val communityWriteResponseDTO = it.body()!!

                                intent = Intent(this, CommunityDetailActivity::class.java)
                                intent.putExtra("postId", communityWriteResponseDTO.postId)
                                startActivity(intent)
                                finish()

                            }
                            it.code() ==  401 -> {
                                App.prefs.setString("accessToken", "")
                                App.prefs.setString("refreshToken", "")
                                intent = Intent(this@CommunityWriteActivity, LoginActivity::class.java)
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
    }
}