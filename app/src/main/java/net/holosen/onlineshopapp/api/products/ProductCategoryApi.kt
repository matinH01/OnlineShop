package net.holosen.onlineshopapp.api.products

import net.holosen.onlineshopapp.model.ApiResponse
import net.holosen.onlineshopapp.model.products.ProductCategory
import retrofit2.http.GET

interface ProductCategoryApi {

    @GET("productCategory?lang=fa")
    suspend fun getCategories() : ApiResponse<ProductCategory>
}