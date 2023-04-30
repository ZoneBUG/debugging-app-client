package com.zonebug.debugging.retrofit.model

import com.zonebug.debugging.DTO.search.SearchResponseDTO
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ModelRetrofitService {

    @Multipart
    @POST("model/search/{model}")
    suspend fun search(
        @Part image : MultipartBody.Part?,
        @Path("model") model : String
    ) : Response<SearchResponseDTO>


}