package com.zonebug.debugging.activity.community

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zonebug.debugging.databinding.ActivityCommunityBinding

class CommunityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}