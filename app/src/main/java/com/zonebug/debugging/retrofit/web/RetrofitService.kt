package com.zonebug.debugging.retrofit.web

import com.zonebug.debugging.DTO.community.*
import com.zonebug.debugging.DTO.user.LoginRequestDTO
import com.zonebug.debugging.DTO.user.RegisterRequestDTO
import com.zonebug.debugging.DTO.user.RegisterResponseDTO
import com.zonebug.debugging.DTO.user.TokenResponseDTO
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

    @POST("user/signup")
    suspend fun signUp(
        @Body registerRequestDTO: RegisterRequestDTO
    ) : Response<RegisterResponseDTO>

    @POST("user/signin")
    suspend fun signIn(
        @Body loginRequestDTO: LoginRequestDTO
    ) : Response<TokenResponseDTO>

//    @GET("user/signin")
//    suspend fun signInNaver(
//        @Body loginRequestDTO: LoginRequestDTO
//    ) : Response<TokenResponseDTO>

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