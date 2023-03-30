package com.zonebug.debugging.DTO.community

import java.util.*

data class CommunityDetailDTO(
    val commentList: ArrayList<Comment>,
    val post: DetailPost
){
    data class DetailPost(
        val postId: Long,
        val nickname: String,
        val title: String,
        val image: String,
        val contents: String,
        val createdAt: Date,
        val updatedAt: Date,
        val isMine: Boolean,
        val hits: Long
    )

    data class Comment(
        val commentId : Long,
        val parentId : Long,
        val nickname : String,
        val contents : String,
        val isMine : Boolean
    )
}
