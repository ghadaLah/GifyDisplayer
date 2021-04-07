package netgem.fr.gify.ui.search

import android.util.Log
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
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
                    private val state: StateLiveData): PositionalDataSource<GifModel>() {

    companion object {
        val pagedList = PagedList.Config
            .Builder()
            .setPageSize(GIF_LIMITS)
            .setEnablePlaceholders(false)
            .build()
        val networkExecutor: ExecutorService = Executors.newFixedThreadPool(4)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<GifModel>) {
        disposable.add(
            repository.getGifList(key, params.startPosition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{state.postValue(StateViewModel.Loading)}
                .subscribe(
                    {
                        callback.onResult(it.data)
                        state.postValue(StateViewModel.Success)
                    },
                    {
                        state.postValue(StateViewModel.Error)
                    }
                )
        )
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<GifModel>) {
        disposable.add(
            repository.getGifList(key, params.requestedStartPosition)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe{state.postValue(StateViewModel.Loading)}
                .subscribe(
                    {
                        callback.onResult(it.data, params.requestedStartPosition)
                        state.postValue(StateViewModel.Success)
                    },
                    {
                        state.postValue(StateViewModel.Error)
                    }
                )
        )
    }
}