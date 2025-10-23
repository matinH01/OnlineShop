package net.holosen.onlineshopapp.config

import androidx.room.Database
import androidx.room.RoomDatabase
import net.holosen.onlineshopapp.dao.BasketEntityDao
import net.holosen.onlineshopapp.dao.UserEntityDao
import net.holosen.onlineshopapp.model.db.BasketEntity
import net.holosen.onlineshopapp.model.db.UserEntity

@Database(version = 1, entities = [BasketEntity::class, UserEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun basketDao(): BasketEntityDao
    abstract fun userDao(): UserEntityDao
}