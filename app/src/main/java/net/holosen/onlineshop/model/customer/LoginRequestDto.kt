package net.holosen.onlineshop.model.customer

data class LoginRequestDto(
    var password: String? = null,
    var username: String? = null
)
