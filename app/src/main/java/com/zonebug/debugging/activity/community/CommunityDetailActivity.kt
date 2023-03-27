package com.zonebug.debugging.activity.community

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zonebug.debugging.R
import com.zonebug.debugging.databinding.ActivityCommunityBinding
import com.zonebug.debugging.databinding.ActivityCommunityDetailBinding

class CommunityDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityDetailBinding

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}