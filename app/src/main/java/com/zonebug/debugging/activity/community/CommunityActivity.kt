package com.zonebug.debugging.activity.community

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.zonebug.debugging.App
import com.zonebug.debugging.DTO.community.CommunityMainDTO
import com.zonebug.debugging.activity.community.adapter.CommunityRecyclerAdapter
import com.zonebug.debugging.activity.login.LoginActivity
import com.zonebug.debugging.databinding.ActivityCommunityBinding
import com.zonebug.debugging.retrofit.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val service = RetrofitObject.getInstance()
        service!!.getCommunityMain().enqueue(object : Callback<CommunityMainDTO> {
            override fun onResponse(
                call: Call<CommunityMainDTO>,
                response: Response<CommunityMainDTO>
            ) {
                if(response.isSuccessful) {
                    val communityMainDTO = response.body()
                    setAdapter("ask", communityMainDTO!!.askList)
                    setAdapter("share", communityMainDTO!!.shareList)


                } else if(response.code() ==  401) {
                    App.prefs.setString("accessToken", "")
                    App.prefs.setString("refreshToken", "")
                    intent = Intent(this@CommunityActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                } else{
                    Log.d("TAG", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + response.isSuccessful)

                }


            }

            override fun onFailure(call: Call<CommunityMainDTO>, t: Throwable) {
                Log.d("TAG", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + t.message.toString())
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