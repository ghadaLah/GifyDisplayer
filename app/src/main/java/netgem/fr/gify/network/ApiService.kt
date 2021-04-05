package netgem.fr.gify.network

import io.reactivex.Single
import netgem.fr.gify.network.data.GifListResponse
import netgem.fr.gify.network.data.GifModel
import netgem.fr.gify.network.data.GifResponse
import netgem.fr.gify.utils.API_KEY
import netgem.fr.gify.utils.GIF_LIMITS
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("random")
    fun getRandomGif(@Query("api_key") apiKey: String = API_KEY): Single<GifResponse>

    @GET("search")
    fun getSearchedGif(@Query("api_key") apiKey: String = API_KEY, @Query("q") q: String, @Query("limit") limit: Int = GIF_LIMITS): Single<GifListResponse>
}