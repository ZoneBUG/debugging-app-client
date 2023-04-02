package com.zonebug.debugging.DTO.user

data class SocialLoginResponseDTO(
    var userId : Long,
    var accessToken : String,
    var refreshToken : String,
    var isMember : Boolean
)
