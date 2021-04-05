package netgem.fr.gify.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import netgem.fr.gify.network.data.GifModel
import netgem.fr.gify.utils.GIF_LIMITS
import javax.inject.Inject

class GifRepository @Inject constructor() {
    @Inject lateinit var apiService: ApiService

    var gifObject = MutableLiveData<GifModel>()
    var error = MutableLiveData<String>()

    fun getGifObject() {
        apiService.getRandomGif()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {response ->
                    Log.d("gifrec", "gif rec $response")
                    if(response != null)
                        gifObject.postValue(response.data)
                },
                {
                    error.postValue("Une erreur est survenue lors de réucpération du random Gif")
                }
            )
    }

    fun getGifList(key: String, limit: Int = GIF_LIMITS) = apiService.getSearchedGif(q = key)
}