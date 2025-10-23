package net.holosen.onlineshop.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.holosen.onlineshop.model.db.BasketEntity

@Dao
interface BasketEntityDao {

    @Insert
    fun add(basketEntity: BasketEntity)

    @Delete
    fun delete(basketEntity: BasketEntity)

    @Query("SELECT * FROM basketentity")
    fun getAll(): Flow<List<BasketEntity>>

    @Query("SELECT * FROM BasketEntity WHERE productId = :productId AND colorId = :colorId AND sizeId = :sizeId LIMIT 1")
    fun findItem(productId: Long, colorId: Long, sizeId: Long): BasketEntity?

    @Query("UPDATE BasketEntity SET quantity = quantity + 1 WHERE id = :id")
    fun incrementQuantity(id: Int)

    @Query("UPDATE BasketEntity SET quantity = quantity - 1 WHERE id = :id")
    fun decrementQuantity(id: Int)

    @Query("DELETE FROM BasketEntity")
    fun deleteAll()
}