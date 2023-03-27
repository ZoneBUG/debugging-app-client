package com.zonebug.debugging.DTO.community

import java.util.*

data class CommunityMainDTO(
    val issueList : List<MainPost>,
    val askList : List<MainPost>,
    val shareList : List<MainPost>
) {
    data class MainPost(
        val postId : Long,
        val tag : String,
        val title : String,
        val createdAt : Date
    )
}


