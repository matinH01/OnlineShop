package net.holosen.onlineshopapp.repository.customer

import net.holosen.onlineshopapp.api.customer.UserApi
import net.holosen.onlineshopapp.api.invoices.InvoiceApi
import net.holosen.onlineshopapp.model.ApiResponse
import net.holosen.onlineshopapp.model.customer.LoginRequestDto
import net.holosen.onlineshopapp.model.customer.User
import net.holosen.onlineshopapp.model.customer.UserDto
import net.holosen.onlineshopapp.model.invoices.Invoice
import net.holosen.onlineshopapp.repository.base.BaseRepository
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
) : BaseRepository() {
    suspend fun getUserInfo(token: String): ApiResponse<User> =
        safeApiCall { api.getUserInfo(prepareToken(token)) }

    suspend fun changePassword(data: UserDto, token: String): ApiResponse<User> =
        safeApiCall { api.changePassword(data, prepareToken(token)) }

    suspend fun login(data: LoginRequestDto): ApiResponse<UserDto> =
        safeApiCall { api.login(data) }

    suspend fun register(data: UserDto): ApiResponse<User> =
        safeApiCall { api.register(data) }

    suspend fun update(data: UserDto, token: String): ApiResponse<User> =
        safeApiCall { api.update(data, prepareToken(token)) }
}