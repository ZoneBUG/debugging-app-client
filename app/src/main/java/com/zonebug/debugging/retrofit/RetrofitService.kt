package com.zonebug.debugging.retrofit

import com.zonebug.debugging.DTO.community.*
import com.zonebug.debugging.DTO.user.LoginRequestDTO
import com.zonebug.debugging.DTO.user.TokenResponseDTO
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

    @POST("community/post")
    suspend fun writePost(
        @Body communityWriteRequestDTO: CommunityWriteRequestDTO
    ) : Response<CommunityWriteResponseDTO>

    @POST("community/comment")
    suspend fun writeComment(
        @Body communityDetailCommentDTO: CommunityDetailCommentDTO
    ) : Response<CommunityDetailCommentResponseDTO>
}