package net.holosen.onlineshop.repository.customer

import net.holosen.onlineshop.api.customer.UserApi
import net.holosen.onlineshop.model.ApiResponse
import net.holosen.onlineshop.model.customer.LoginRequestDto
import net.holosen.onlineshop.model.customer.User
import net.holosen.onlineshop.model.customer.UserDto
import net.holosen.onlineshop.repository.base.BaseRepository
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