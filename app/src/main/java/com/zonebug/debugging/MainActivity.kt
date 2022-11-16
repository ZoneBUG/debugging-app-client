package com.zonebug.debugging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zonebug.debugging.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val transaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        transaction.add(R.id.Main_View, HomeFragment()).commit()
    }
}