package net.holosen.onlineshop.api.site

import net.holosen.onlineshop.model.ApiResponse
import net.holosen.onlineshop.model.site.Slider
import retrofit2.http.GET

interface SliderApi {

    @GET("slider?lang=fa")
    suspend fun getSliders(): ApiResponse<Slider>
}