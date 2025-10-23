package net.holosen.onlineshop.config

import androidx.room.Database
import androidx.room.RoomDatabase
import net.holosen.onlineshop.dao.BasketEntityDao
import net.holosen.onlineshop.dao.UserEntityDao
import net.holosen.onlineshop.model.db.BasketEntity
import net.holosen.onlineshop.model.db.UserEntity

@Database(version = 1, entities = [BasketEntity::class, UserEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun basketDao(): BasketEntityDao
    abstract fun userDao(): UserEntityDao
}