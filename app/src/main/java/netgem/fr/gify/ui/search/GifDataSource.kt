package netgem.fr.gify.ui.search

import android.util.Log
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import netgem.fr.gify.network.GifRepository
import netgem.fr.gify.network.data.GifModel
import netgem.fr.gify.utils.GIF_LIMITS
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class GifDataSource(val repository: GifRepository,
                    val key: String,
                    private val disposable: CompositeDisposable,
                    private val state: StateLiveData): PageKeyedDataSource<Int, GifModel>() {

    companion object {
        val pagedList = PagedList.Config
            .Builder()
            .setPageSize(GIF_LIMITS)
            .build()
        val networkExecutor: ExecutorService = Executors.newFixedThreadPool(4)
    }
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GifModel>
    ) {
        val page = 0
        disposable.add(
            repository.getGifList(key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{state.postValue(StateViewModel.Loading)}
            .subscribe(
                {
                    callback.onResult(it.data, null, page+1)
                    state.postValue(StateViewModel.Success)
                },
                {
                    state.postValue(StateViewModel.Error)
                }
            )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GifModel>) {
        val page = params.key
        disposable.add(
            repository.getGifList(key, limit = GIF_LIMITS*page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{state.postValue(StateViewModel.Loading)}
            .subscribe(
                {
                    callback.onResult(it.data, page+1)
                    state.postValue(StateViewModel.Success)
                },
                {
                    state.postValue(StateViewModel.Error)
                }
            )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GifModel>) {
    }
}