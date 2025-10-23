package net.holosen.onlineshopapp.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import net.holosen.onlineshopapp.model.db.BasketEntity
import net.holosen.onlineshopapp.model.products.Product
import net.holosen.onlineshopapp.model.products.ProductColor
import net.holosen.onlineshopapp.model.products.ProductSize
import net.holosen.onlineshopapp.repository.basket.BasketEntityRepository
import javax.inject.Inject


@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository: BasketEntityRepository
) : ViewModel() {

    val basket = repository.getAllBasketList()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addToBasket(product: Product?, color: ProductColor?, size: ProductSize?) {
        viewModelScope.launch(Dispatchers.IO) {
            val oldItem = repository.findBasketItem(
                product?.id ?: 0,
                color?.id ?: 0,
                size?.id ?: 0
            )
            if (oldItem != null) {
                repository.incrementQuantity(oldItem)
            } else {
                repository.insert(
                    BasketEntity(
                        productId = product?.id!!,
                        sizeId = size?.id!!,
                        colorId = color?.id!!,
                        quantity = 1,
                        image = product.image,
                        price = product.price,
                        title = product.title,
                        colorHex = color.hexValue,
                        size = size.title
                    )
                )
            }
        }
    }

    fun increaseQuantity(item: BasketEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.incrementQuantity(item)
        }
    }

    fun decreaseQuantity(item: BasketEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.decrementQuantity(item)
        }
    }

    fun deleteItem(item: BasketEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(item)
        }
    }
}