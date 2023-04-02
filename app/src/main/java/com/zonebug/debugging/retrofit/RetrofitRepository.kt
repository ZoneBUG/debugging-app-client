package com.zonebug.debugging.retrofit

import com.zonebug.debugging.DTO.community.*
import com.zonebug.debugging.DTO.user.LoginRequestDTO
import com.zonebug.debugging.DTO.user.RegisterRequestDTO
import com.zonebug.debugging.DTO.user.RegisterResponseDTO
import com.zonebug.debugging.DTO.user.TokenResponseDTO
import retrofit2.Response

object RetrofitRepository {

    suspend fun signUp(registerRequestDTO: RegisterRequestDTO) : Response<RegisterResponseDTO> {
        return RetrofitObject.api.signUp(registerRequestDTO)
    }

    suspend fun signIn(loginRequestDTO: LoginRequestDTO) : Response<TokenResponseDTO> {
        return RetrofitObject.api.signIn(loginRequestDTO)
    }

//    suspend fun signInNaver() : Response<TokenResponseDTO> {
//        return RetrofitObject.api.signInNaver()
//    }

    suspend fun getCommunityMain() : Response<CommunityMainDTO> {
        return RetrofitObject.api.getCommunityMain()
    }

    suspend fun getCommunityList(tag: String, pageNum: Long) : Response<CommunityListDTO> {
        return RetrofitObject.api.getCommunityList(tag, pageNum)
    }

    suspend fun getPost(postId: Long) : Response<CommunityDetailDTO> {
        return RetrofitObject.api.getPost(postId)
    }

    suspend fun writePost(communityWriteRequestDTO: CommunityWriteRequestDTO) : Response<CommunityWriteResponseDTO> {
        return RetrofitObject.api.writePost(communityWriteRequestDTO)
    }

    suspend fun writeComment(communityDetailCommentDTO: CommunityDetailCommentDTO) : Response<CommunityDetailCommentResponseDTO> {
        return RetrofitObject.api.writeComment(communityDetailCommentDTO)
    }
}