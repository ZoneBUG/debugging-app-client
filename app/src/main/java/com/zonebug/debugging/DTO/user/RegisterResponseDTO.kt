package com.zonebug.debugging.DTO.user

data class RegisterResponseDTO(
    var email: String,
    var nickname: String,
    var period: Int,
    var type: String,
    var refreshToken: String
)
