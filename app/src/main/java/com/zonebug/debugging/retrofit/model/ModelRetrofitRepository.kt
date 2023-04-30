package com.zonebug.debugging.retrofit.model

import com.zonebug.debugging.DTO.search.SearchResponseDTO
import okhttp3.MultipartBody
import retrofit2.Response

object ModelRetrofitRepository {

    suspend fun search(image : MultipartBody.Part?, model : String) : Response<SearchResponseDTO> {
        return ModelRetrofitObject.api.search(image, model)
    }

}