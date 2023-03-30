package com.zonebug.debugging.DTO.community

import java.util.*

data class CommunityListDTO(
    val postList: List<ListPost>,
    val totalPages: Long,
    val totalResults: Long
){
    data class ListPost(
        val postId : Long,
        val nickname : String,
        val title : String,
        val createdAt : Date
    )
}
