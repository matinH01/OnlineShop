package net.holosen.onlineshop.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import net.holosen.onlineshop.model.products.Product
import net.holosen.onlineshop.repository.products.ProductRepository
import net.holosen.onlineshop.ui.state.DataUiState
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    var products by mutableStateOf<DataUiState<Product>>(DataUiState())
        private set

    private var pageIndex = 0
    private val pageSize = 5
    private var endReached = false

    fun loadProducts(categoryId: Long) {
        if (products.isLoading || endReached) return

        loadData(stateSetter = {
            if (it.isLoading) {
                products = products.copy(isLoading = true)
            } else {
                if (it.data?.isEmpty() == true) {
                    endReached = true
                }
                pageIndex++
                val current = products.data?.toMutableList() ?: mutableListOf()
                current.addAll(it.data ?: listOf())
                products = products.copy(isLoading = false, data = current)
            }
        }) {
            productRepository.getProductsByCategoryId(categoryId, pageIndex, pageSize)
        }
    }
}