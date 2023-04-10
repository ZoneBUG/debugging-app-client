package com.zonebug.debugging.activity.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zonebug.debugging.R
import com.zonebug.debugging.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySearchBinding
    var isBefore : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.Search_MainView, SearchBeforeFragment()).commit()


        // 하단 버튼 클릭
        binding.SearchBtn.setOnClickListener {
            isBefore = !isBefore
            switchFragment()
        }



//        val storage = Firebase.storage

    }

    private fun switchFragment() {
        val transaction = supportFragmentManager.beginTransaction()

        if(isBefore) {
            transaction.replace(R.id.Search_MainView, SearchBeforeFragment())
                .addToBackStack(null)
                .commit()
            binding.SearchBtn.text = "검색하기"
        } else {
            transaction.replace(R.id.Search_MainView, SearchAfterFragment())
                .addToBackStack(null)
                .commit()
            binding.SearchBtn.text = "다른 벌레 검색하기"
        }

    }


    override fun onBackPressed() {
        finish()
    }
}