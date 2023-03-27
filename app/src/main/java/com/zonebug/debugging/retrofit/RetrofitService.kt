package com.zonebug.debugging.retrofit

import com.zonebug.debugging.DTO.community.CommunityListDTO
import com.zonebug.debugging.DTO.community.CommunityMainDTO
import com.zonebug.debugging.DTO.user.LoginRequestDTO
import com.zonebug.debugging.DTO.user.TokenResponseDTO
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @POST("user/signin")
    fun signIn(
        @Body loginRequestDTO: LoginRequestDTO
    ) : Call<TokenResponseDTO>


    @GET("community/")
    fun getCommunityMain() : Call<CommunityMainDTO>

    @GET("community/tag/{tag}")
    fun getCommunityList(
        @Path("tag") tag: String,
        @Query("pageNum") pageNum: Long
    ) : Call<CommunityListDTO>

}