package net.holosen.onlineshop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.holosen.onlineshop.model.db.UserEntity

@Dao
interface UserEntityDao {

    @Insert
    fun add(data: UserEntity)

    @Query("delete from UserEntity")
    fun deleteAll()

    @Query("SELECT * FROM UserEntity limit 1")
    fun getCurrentUser() : Flow<UserEntity?>
}