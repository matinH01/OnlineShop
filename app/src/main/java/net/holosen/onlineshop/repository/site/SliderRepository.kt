package net.holosen.onlineshop.repository.site

import net.holosen.onlineshop.api.site.SliderApi
import net.holosen.onlineshop.model.ApiResponse
import net.holosen.onlineshop.model.site.Slider
import net.holosen.onlineshop.repository.base.BaseRepository
import javax.inject.Inject

class SliderRepository @Inject constructor(
    private val api: SliderApi
) : BaseRepository() {
    suspend fun getSliders(): ApiResponse<Slider> =
        safeApiCall { api.getSliders() }
}