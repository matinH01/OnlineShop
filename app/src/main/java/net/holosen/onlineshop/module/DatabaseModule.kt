package net.holosen.onlineshop.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.holosen.onlineshop.config.AppDatabase
import net.holosen.onlineshop.dao.BasketEntityDao
import net.holosen.onlineshop.dao.UserEntityDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "online_shop_db"
        ).build()
    }

    @Provides
    fun provideBasketDao(db: AppDatabase): BasketEntityDao {
        return db.basketDao()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): UserEntityDao {
        return db.userDao()
    }
}