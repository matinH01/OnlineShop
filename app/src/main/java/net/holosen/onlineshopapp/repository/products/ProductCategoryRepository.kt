package net.holosen.onlineshopapp.repository.products

import net.holosen.onlineshopapp.api.products.ProductCategoryApi
import net.holosen.onlineshopapp.model.ApiResponse
import net.holosen.onlineshopapp.model.products.ProductCategory
import net.holosen.onlineshopapp.repository.base.BaseRepository
import javax.inject.Inject

class ProductCategoryRepository @Inject constructor(
    private val api: ProductCategoryApi
) : BaseRepository() {
    suspend fun getCategories(): ApiResponse<ProductCategory> =
        safeApiCall { api.getCategories() }
}