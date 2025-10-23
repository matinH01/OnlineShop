package net.holosen.onlineshop.repository.products

import net.holosen.onlineshop.api.products.ProductApi
import net.holosen.onlineshop.model.ApiResponse
import net.holosen.onlineshop.model.products.Product
import net.holosen.onlineshop.repository.base.BaseRepository
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