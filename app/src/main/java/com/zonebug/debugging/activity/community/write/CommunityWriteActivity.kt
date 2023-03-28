package com.zonebug.debugging.activity.community.write

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zonebug.debugging.databinding.ActivityCommunityWriteBinding

class CommunityWriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommunityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tag_title = intent.getStringExtra("tag_title")
        binding.ToolbarCommunityWriteText.text = tag_title
    }
}