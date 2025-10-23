package net.holosen.onlineshop.api.products

import net.holosen.onlineshop.model.ApiResponse
import net.holosen.onlineshop.model.products.ProductCategory
import retrofit2.http.GET

interface ProductCategoryApi {

    @GET("productCategory?lang=fa")
    suspend fun getCategories() : ApiResponse<ProductCategory>
}