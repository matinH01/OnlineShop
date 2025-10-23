package net.holosen.onlineshopapp.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.holosen.onlineshopapp.model.products.Product
import net.holosen.onlineshopapp.model.products.ProductCategory
import net.holosen.onlineshopapp.model.site.Slider
import net.holosen.onlineshopapp.repository.products.ProductCategoryRepository
import net.holosen.onlineshopapp.repository.products.ProductRepository
import net.holosen.onlineshopapp.repository.site.SliderRepository
import net.holosen.onlineshopapp.ui.state.DataUiState
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








