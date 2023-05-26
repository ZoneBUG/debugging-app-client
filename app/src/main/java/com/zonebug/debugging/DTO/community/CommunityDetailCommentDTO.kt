package com.zonebug.debugging.DTO.community

import java.util.*

data class CommunityDetailCommentDTO(
    var postId : Long,
    var parentId : Long,
    var contents : String
)
