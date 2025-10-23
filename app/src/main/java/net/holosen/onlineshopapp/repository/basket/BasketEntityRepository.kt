package net.holosen.onlineshopapp.repository.basket

import androidx.compose.ui.input.pointer.PointerId
import kotlinx.coroutines.flow.Flow
import net.holosen.onlineshopapp.dao.BasketEntityDao
import net.holosen.onlineshopapp.model.db.BasketEntity
import javax.inject.Inject

class BasketEntityRepository @Inject constructor(
    private val dao: BasketEntityDao
) {

    fun getAllBasketList(): Flow<List<BasketEntity>> {
        return dao.getAll()
    }

    suspend fun findBasketItem(productId: Long, colorId: Long, sizeId: Long): BasketEntity? {
        return dao.findItem(productId, colorId, sizeId)
    }

    fun incrementQuantity(item: BasketEntity) {
        dao.incrementQuantity(item.id)
    }

    fun decrementQuantity(item: BasketEntity) {
        if (item.quantity == 1) {
            return
        }
        dao.decrementQuantity(item.id)
    }

    fun insert(item: BasketEntity) {
        dao.add(item)
    }

    fun delete(item: BasketEntity) {
        dao.delete(item)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

}