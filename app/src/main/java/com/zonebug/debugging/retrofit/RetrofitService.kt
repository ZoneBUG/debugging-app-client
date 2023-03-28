package com.zonebug.debugging.retrofit

import com.zonebug.debugging.DTO.community.CommunityDetailDTO
import com.zonebug.debugging.DTO.community.CommunityListDTO
import com.zonebug.debugging.DTO.community.CommunityMainDTO
import com.zonebug.debugging.DTO.user.LoginRequestDTO
import com.zonebug.debugging.DTO.user.TokenResponseDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

    @POST("user/signin")
    suspend fun signIn(
        @Body loginRequestDTO: LoginRequestDTO
    ) : Response<TokenResponseDTO>


    @GET("community/")
    suspend fun getCommunityMain() : Response<CommunityMainDTO>

    @GET("community/tag/{tag}")
    suspend fun getCommunityList(
        @Path("tag") tag: String,
        @Query("pageNum") pageNum: Long
    ) : Response<CommunityListDTO>

    @GET("community/post/{postId}")
    suspend fun getPost(
        @Path("postId") postId: Long,
    ) : Response<CommunityDetailDTO>
}