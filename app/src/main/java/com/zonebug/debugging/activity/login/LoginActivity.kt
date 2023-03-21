package com.zonebug.debugging.activity.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.zonebug.debugging.DTO.LoginRequestDTO
import com.zonebug.debugging.DTO.TokenResponseDTO
import com.zonebug.debugging.retrofit.RetrofitObject
import com.zonebug.debugging.activity.MainActivity
import com.zonebug.debugging.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Login
        binding.LoginButton.setOnClickListener {
            login(this)
        }


    }

    fun login(context : Context) {
        val service = RetrofitObject.getInstance()
        val email : String = binding.LoginInputEmail.text.toString()
        val password : String = binding.LoginInputPw.text.toString()

        val loginRequestDTO = LoginRequestDTO(email = email, password = password)

        service!!.signIn(loginRequestDTO).enqueue(object : Callback<TokenResponseDTO> {
            override fun onResponse(
                call: Call<TokenResponseDTO>,
                response: Response<TokenResponseDTO>
            ) {
                if(response.isSuccessful) {
                    val tokenResponseDTO = response.body()
                    Log.d("YMC", "____________________ onResponse Success " + tokenResponseDTO.toString())

                    intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.d("YMC", "____________________ onResponse Fail " + response.isSuccessful)
                }
            }

            override fun onFailure(call: Call<TokenResponseDTO>, t: Throwable) {
                Log.d("YMC", "____________________ onFailure : " + t.message.toString())
            }
        })
    }


}