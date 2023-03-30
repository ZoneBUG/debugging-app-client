package com.zonebug.debugging.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.zonebug.debugging.App
import com.zonebug.debugging.R
import com.zonebug.debugging.activity.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent_login = Intent(baseContext, LoginActivity::class.java)
            val intent_main = Intent(baseContext, MainActivity::class.java)
            val accessToken = App.prefs.getString("accessToken", "")

            if(accessToken == "") startActivity(intent_login)
            else startActivity(intent_main)
            finish()
        }, 3000)

    }
}