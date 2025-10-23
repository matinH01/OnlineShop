package net.holosen.onlineshop.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import net.holosen.onlineshop.model.products.Product
import net.holosen.onlineshop.model.products.ProductColor
import net.holosen.onlineshop.model.products.ProductSize
import net.holosen.onlineshop.repository.products.ProductRepository
import javax.inject.Inject

@HiltViewModel
class SingleProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    var product by mutableStateOf<Product?>(null)

    var selectedSize by mutableStateOf<ProductSize?>(null)
    var selectedColor by mutableStateOf<ProductColor?>(null)

    fun loadProduct(id: Long) {
        loadData(stateSetter = {
            product = it.data?.firstOrNull()
            product?.sizes?.firstOrNull()?.let { s ->
                selectedSize = s
            }
            product?.colors?.firstOrNull()?.let { c ->
                selectedColor = c
            }
        }) {
            productRepository.getProductById(id)
        }
    }
}