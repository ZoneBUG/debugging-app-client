package com.zonebug.debugging.activity.aboutus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zonebug.debugging.R
import com.zonebug.debugging.databinding.ActivityAboutUsBinding
import com.zonebug.debugging.databinding.ActivityCommunityDetailBinding

class AboutUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.AboutUsBtnCharge.setOnClickListener{
            val dlg = MyDialog(this)
            dlg.start("디버깅은 기기 렌탈을 통한 무인 서비스로,\n인건비가 포함되지 않은\n저렴한 이용료를 자랑힙니다!\n\n월 19,900원의 구독료를 통해\n디버깅을 이용해보세요!")
        }

        binding.AboutUsBtnFAQ.setOnClickListener{
            val dlg = MyDialog(this)
            dlg.start("디버깅에게 궁금한 점이 있으시면\nzonebugg@gmail.com 주소로\n메일을 보내주세요!")
        }
    }

}