package net.holosen.onlineshop.repository.customer

import kotlinx.coroutines.flow.Flow
import net.holosen.onlineshop.dao.UserEntityDao
import net.holosen.onlineshop.model.db.UserEntity
import javax.inject.Inject

class UserEntityRepository @Inject constructor(
    private val dao: UserEntityDao
){
    fun insert(data: UserEntity) {
        dao.deleteAll()
        return dao.add(data)
    }

    fun getCurrentUser(): Flow<UserEntity?> {
        return dao.getCurrentUser()
    }

    fun deleteAll(){
        dao.deleteAll()
    }
}