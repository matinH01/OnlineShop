package net.holosen.onlineshopapp.api.site

import net.holosen.onlineshopapp.model.ApiResponse
import net.holosen.onlineshopapp.model.site.Slider
import retrofit2.http.GET

interface SliderApi {

    @GET("slider?lang=fa")
    suspend fun getSliders(): ApiResponse<Slider>
}