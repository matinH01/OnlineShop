package net.holosen.onlineshopapp.model.customer

data class LoginRequestDto(
    var password: String? = null,
    var username: String? = null
)
