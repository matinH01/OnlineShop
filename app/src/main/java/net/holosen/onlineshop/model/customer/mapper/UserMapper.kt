package net.holosen.onlineshop.model.customer.mapper

import net.holosen.onlineshop.model.customer.UserDto
import net.holosen.onlineshop.model.db.UserEntity

fun UserDto.toEntity(): UserEntity {
    return UserEntity(
        username = this.username,
        address = this.address,
        firstName = this.firstName,
        lastName = this.lastName,
        phone = this.phone,
        postalCode = this.postalCode,
        customerId = this.customerId,
        userId = this.id,
        token = this.token
    )
}