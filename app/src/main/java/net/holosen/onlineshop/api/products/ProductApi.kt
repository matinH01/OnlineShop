package net.holosen.onlineshop.api.products

import net.holosen.onlineshop.model.ApiResponse
import net.holosen.onlineshop.model.products.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("product?lang=fa")
    suspend fun getProducts(
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): ApiResponse<Product>

    @GET("product/{id}")
    suspend fun getProductById(
        @Path("id") id: Long
    ): ApiResponse<Product>

    @GET("product/cat/{id}")
    suspend fun getProductsByCategoryId(
        @Path("id") id: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): ApiResponse<Product>

    @GET("product/new?lang=fa")
    suspend fun getNewProducts(): ApiResponse<Product>

    @GET("product/popular?lang=fa")
    suspend fun getPopularProducts(): ApiResponse<Product>
}