package net.holosen.onlineshopapp.repository.site

import net.holosen.onlineshopapp.api.site.SliderApi
import net.holosen.onlineshopapp.model.ApiResponse
import net.holosen.onlineshopapp.model.site.Slider
import net.holosen.onlineshopapp.repository.base.BaseRepository
import javax.inject.Inject

class SliderRepository @Inject constructor(
    private val api: SliderApi
) : BaseRepository() {
    suspend fun getSliders(): ApiResponse<Slider> =
        safeApiCall { api.getSliders() }
}