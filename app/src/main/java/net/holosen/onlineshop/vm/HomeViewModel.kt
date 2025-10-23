package net.holosen.onlineshop.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import net.holosen.onlineshop.model.products.Product
import net.holosen.onlineshop.model.products.ProductCategory
import net.holosen.onlineshop.model.site.Slider
import net.holosen.onlineshop.repository.products.ProductCategoryRepository
import net.holosen.onlineshop.repository.products.ProductRepository
import net.holosen.onlineshop.repository.site.SliderRepository
import net.holosen.onlineshop.ui.state.DataUiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sliderRepository: SliderRepository,
    private val productCategoryRepository: ProductCategoryRepository,
    private val productRepository: ProductRepository
) : BaseViewModel() {

    var sliders by mutableStateOf<DataUiState<Slider>>(DataUiState())
        private set

    var productCategories by mutableStateOf<DataUiState<ProductCategory>>(DataUiState())
        private set

    var products by mutableStateOf<DataUiState<Product>>(DataUiState())
        private set


    init {
        loadSliders()
        loadProductCategories()
        loadAllProducts()
    }

    fun loadSliders() {
        loadData(stateSetter = { sliders = it }) {
            sliderRepository.getSliders()
        }
    }

    fun loadProductCategories() {
        loadData(stateSetter = { productCategories = it }) {
            productCategoryRepository.getCategories()
        }
    }

    fun loadAllProducts() {
        loadData(stateSetter = { products = it }) {
            productRepository.getProducts(0, 6)
        }
    }

    fun loadNewProducts() {
        loadData(stateSetter = { products = it }) {
            productRepository.getNewProducts()
        }
    }

    fun loadPopularProducts() {
        loadData(stateSetter = { products = it }) {
            productRepository.getPopularProducts()
        }
    }
}








