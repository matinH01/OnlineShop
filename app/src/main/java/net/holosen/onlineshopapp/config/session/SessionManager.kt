package net.holosen.onlineshopapp.config.session

import net.holosen.onlineshopapp.repository.customer.UserEntityRepository
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