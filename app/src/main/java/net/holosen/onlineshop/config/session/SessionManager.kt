package net.holosen.onlineshop.config.session

import net.holosen.onlineshop.repository.customer.UserEntityRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val repository: UserEntityRepository
) {

    init {
        SessionManagerProvider.sessionManager = this
    }

    suspend fun logout() {
        repository.deleteAll()
    }
}