package com.zonebug.debugging.retrofit

import com.zonebug.debugging.DTO.community.CommunityMainDTO
import com.zonebug.debugging.DTO.user.LoginRequestDTO
import com.zonebug.debugging.DTO.user.TokenResponseDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @POST("user/signin")
    fun signIn(
        @Body loginRequestDTO: LoginRequestDTO
    ) : Call<TokenResponseDTO>


    @GET("community/")
    fun getCommunityMain() : Call<CommunityMainDTO>

}