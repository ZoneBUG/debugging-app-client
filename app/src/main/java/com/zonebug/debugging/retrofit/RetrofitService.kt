package com.zonebug.debugging.retrofit

import com.zonebug.debugging.DTO.LoginRequestDTO
import com.zonebug.debugging.DTO.TokenResponseDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {

    @POST("user/signin")
    fun signIn(
        @Body loginRequestDTO: LoginRequestDTO
    ) : Call<TokenResponseDTO>


}