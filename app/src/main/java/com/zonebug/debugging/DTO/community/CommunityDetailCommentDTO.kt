package com.zonebug.debugging.DTO.community

data class CommunityDetailCommentDTO(
    var postId : Long,
    var parentId : Long,
    var contents : String
)
