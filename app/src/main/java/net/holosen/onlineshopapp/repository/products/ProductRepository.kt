package net.holosen.onlineshopapp.repository.products

import net.holosen.onlineshopapp.api.products.ProductApi
import net.holosen.onlineshopapp.model.ApiResponse
import net.holosen.onlineshopapp.model.products.Product
import net.holosen.onlineshopapp.repository.base.BaseRepository
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApi
) : BaseRepository() {

    suspend fun getProducts(pageIndex: Int, pageSize: Int): ApiResponse<Product> =
        safeApiCall { api.getProducts(pageIndex, pageSize) }

    suspend fun getProductById(id: Long): ApiResponse<Product> =
        safeApiCall { api.getProductById(id) }

    suspend fun getProductsByCategoryId(
        categoryId: Long,
        pageIndex: Int,
        pageSize: Int
    ): ApiResponse<Product> =
        safeApiCall { api.getProductsByCategoryId(categoryId, pageIndex, pageSize) }

    suspend fun getNewProducts(): ApiResponse<Product> =
        safeApiCall { api.getNewProducts() }

    suspend fun getPopularProducts(): ApiResponse<Product> =
        safeApiCall { api.getPopularProducts() }
}