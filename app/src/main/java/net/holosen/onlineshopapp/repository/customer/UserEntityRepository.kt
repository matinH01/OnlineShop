package net.holosen.onlineshopapp.repository.customer

import kotlinx.coroutines.flow.Flow
import net.holosen.onlineshopapp.dao.UserEntityDao
import net.holosen.onlineshopapp.model.db.UserEntity
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