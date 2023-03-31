package com.zonebug.debugging.activity.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zonebug.debugging.App
import com.zonebug.debugging.DTO.user.LoginRequestDTO
import com.zonebug.debugging.activity.MainActivity
import com.zonebug.debugging.activity.register.RegisterActivity
import com.zonebug.debugging.databinding.ActivityLoginBinding
import com.zonebug.debugging.retrofit.RetrofitRepository

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: TokenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Login
        binding.LoginButton.setOnClickListener {
            login(this)
        }


        // Register
        binding.LoginTextRegister.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun login(context : Context) {
        val email : String = binding.LoginInputEmail.text.toString()
        val password : String = binding.LoginInputPw.text.toString()

        val loginRequestDTO = LoginRequestDTO(email = email, password = password)

        val repository = RetrofitRepository
        val viewModelFactory = TokenViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(TokenViewModel::class.java)
        viewModel.signIn(loginRequestDTO)
        viewModel.myResponse.observe(this, Observer {
            if(it.isSuccessful) {
                val tokenResponseDTO = it.body()!!

                App.prefs.setString("accessToken", tokenResponseDTO.accessToken)
                App.prefs.setString("refreshToken", tokenResponseDTO.refreshToken)

                intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Log.d("TAG", "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ " + it.isSuccessful)

            }
        })
    }


}