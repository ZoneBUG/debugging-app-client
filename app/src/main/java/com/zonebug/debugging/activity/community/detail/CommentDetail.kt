package com.zonebug.debugging.activity.community.detail

import com.zonebug.debugging.DTO.community.CommunityDetailDTO
import java.util.*

data class CommentDetail(

    val postId: Long,
    val comment: CommunityDetailDTO.Comment,
    val childCommentList: List<CommunityDetailDTO.Comment>

)
