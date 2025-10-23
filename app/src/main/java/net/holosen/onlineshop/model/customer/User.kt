package net.holosen.onlineshop.model.customer

data class User(
    var id: Long?,
    var password: String?,
    var username: String?,
    var customer: Customer? = null
)
