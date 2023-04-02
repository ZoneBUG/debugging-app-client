package com.zonebug.debugging.activity.community.detail

import com.zonebug.debugging.DTO.community.CommunityDetailDTO

data class CommentDetail(

    val comment : CommunityDetailDTO.Comment,
    val childCommentList : List<CommunityDetailDTO.Comment>

)
