package com.zonebug.debugging.activity.community

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.zonebug.debugging.App
import com.zonebug.debugging.DTO.community.CommunityListDTO
import com.zonebug.debugging.activity.community.adapter.CommunityListRecyclerAdapter
import com.zonebug.debugging.activity.login.LoginActivity
import com.zonebug.debugging.databinding.ActivityCommunityListBinding
import com.zonebug.debugging.retrofit.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommunityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tag = intent.getStringExtra("tag")
        if(tag!! == "issue") binding.ToolbarCommunityListText.text = "오늘의 Issue"
        else if(tag!! == "ask") binding.ToolbarCommunityListText.text = "궁금해요!"
        else if(tag!! == "share") binding.ToolbarCommunityListText.text = "공유해요!"
        fetchMoreList(tag!!)
    }

    private fun fetchMoreList(tag: String) {
        val service = RetrofitObject.getInstance()
        service!!.getCommunityList(tag, 0).enqueue(object : Callback<CommunityListDTO> {
            override fun onResponse(
                call: Call<CommunityListDTO>,
                response: Response<CommunityListDTO>
            ) {
                if(response.isSuccessful) {
                    val communityListDTO = response.body()
                    setListAdapter(communityListDTO!!.postList)

                } else if(response.code() ==  401) {
                    App.prefs.setString("accessToken", "")
                    App.prefs.setString("refreshToken", "")
                    intent = Intent(this@CommunityListActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Log.d("TAG", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + response.isSuccessful)

                }
            }

            override fun onFailure(call: Call<CommunityListDTO>, t: Throwable) {
                Log.d("TAG", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + t.message.toString())
            }
        })
    }

    private fun setListAdapter(postList: List<CommunityListDTO.ListPost>) {
        binding.CommunityListRV.adapter = CommunityListRecyclerAdapter(postList)
        binding.CommunityListRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.CommunityListRV.setHasFixedSize(true)
    }
}