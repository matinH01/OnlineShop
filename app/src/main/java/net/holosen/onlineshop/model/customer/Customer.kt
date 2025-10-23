package net.holosen.onlineshop.model.customer

data class Customer(
    var id:Long?,
    var address: String?,
    var firstName: String?,
    var lastName: String?,
    var phone: String?,
    var postalCode: String?
)
