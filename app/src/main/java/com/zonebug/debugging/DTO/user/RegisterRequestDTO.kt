package com.zonebug.debugging.DTO.user

data class RegisterRequestDTO(
    var email: String,
    var password: String,
    var nickname: String,
    var period: Int,
    var type: String
)
