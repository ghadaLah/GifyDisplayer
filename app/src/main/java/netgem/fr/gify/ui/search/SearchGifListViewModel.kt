package netgem.fr.gify.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import netgem.fr.gify.network.GifRepository
import netgem.fr.gify.network.data.GifModel
import javax.inject.Inject

class SearchGifListViewModel @Inject constructor(val repository: GifRepository): ViewModel() {
    lateinit var gifs: LiveData<PagedList<GifModel>>
    private val disposables = CompositeDisposable()
    val state = StateLiveData()

    fun getGifList(key: String):  LiveData<PagedList<GifModel>>? {
        val factory = GifDataSourceFactory(repository, key, disposables, state)
        val config = GifDataSource.pagedList
        gifs = LivePagedListBuilder(factory, config)
            .setFetchExecutor(GifDataSource.networkExecutor)
            .build()
        return gifs
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}