package com.zonebug.debugging.activity.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zonebug.debugging.DTO.user.RegisterRequestDTO
import com.zonebug.debugging.activity.login.LoginActivity
import com.zonebug.debugging.databinding.ActivityRegisterBinding
import com.zonebug.debugging.retrofit.web.RetrofitRepository

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    val repository = RetrofitRepository
    var isCheckedNickname = false
    var checkedNickname = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.RegisterContainerBtnNickname.setOnClickListener {
            checkNickname()
        }

        binding.RegisterBtnFin.setOnClickListener {
            signIn()
        }
    }

    private fun checkNickname() {
        checkedNickname = ""
        val nickname = binding.RegisterContainerInputNickname.text.toString()

        if(nickname.isBlank()) {
            Toast.makeText(this@RegisterActivity, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
            isCheckedNickname = false
            return
        } else {
            // 닉네임 중복 확인

            isCheckedNickname = true
            checkedNickname = nickname
        }
    }

    private fun signIn() {
        val email = binding.RegisterContainerInputEmail.text.toString()
        val password = binding.RegisterContainerInputPw.text.toString()
//        val period = binding.RegisterContainerInputPeriod.text

        if(email.isBlank()) {
            Toast.makeText(this@RegisterActivity, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        } else if(password.isBlank()) {
            Toast.makeText(this@RegisterActivity, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        } else if(password.length < 8) {
            Toast.makeText(this@RegisterActivity, "8자 이상의 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        } else if(!isCheckedNickname) {
            Toast.makeText(this@RegisterActivity, "닉네임 중복 확인을 해주세요", Toast.LENGTH_SHORT).show()
            return
        }
//        else if(period.isNullOrBlank()) {
//            Toast.makeText(this@RegisterActivity, "기간을 입력해주세요", Toast.LENGTH_SHORT).show()
//        }
        else {
            val registerRequestDTO = RegisterRequestDTO(
                email = email,
                password = password,
                nickname = checkedNickname,
                period = 0,
                type = "email"
            )

            val viewModelFactory = RegisterViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
            viewModel.signUp(registerRequestDTO)
            viewModel.myResponse.observe(this, Observer {
                val registerResponseDTO = it.body()!!

                Toast.makeText(this@RegisterActivity, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()

                intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            })
        }



    }
}