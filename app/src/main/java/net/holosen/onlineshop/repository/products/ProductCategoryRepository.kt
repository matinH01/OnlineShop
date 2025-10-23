package net.holosen.onlineshop.repository.products

import net.holosen.onlineshop.api.products.ProductCategoryApi
import net.holosen.onlineshop.model.ApiResponse
import net.holosen.onlineshop.model.products.ProductCategory
import net.holosen.onlineshop.repository.base.BaseRepository
import javax.inject.Inject

class ProductCategoryRepository @Inject constructor(
    private val api: ProductCategoryApi
) : BaseRepository() {
    suspend fun getCategories(): ApiResponse<ProductCategory> =
        safeApiCall { api.getCategories() }
}