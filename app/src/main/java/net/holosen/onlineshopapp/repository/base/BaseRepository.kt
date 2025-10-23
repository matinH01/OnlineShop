package net.holosen.onlineshopapp.repository.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.holosen.onlineshopapp.config.session.SessionManagerProvider
import net.holosen.onlineshopapp.model.ApiResponse

open class BaseRepository {

    fun prepareToken(token: String): String {
        var result = token
        if (!token.lowercase().startsWith("bearer")) {
            result = "Bearer $token"
        }
        return result
    }

    suspend fun <T> safeApiCall(apiCall: suspend () -> ApiResponse<T>): ApiResponse<T> {
        return try {
            apiCall()
        } catch (e: Exception) {
            e.message?.contains("417")?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    SessionManagerProvider.sessionManager.logout()
                }
            }
            ApiResponse(status = "EXCEPTION", message = e.message)
        }
    }
}