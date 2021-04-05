package netgem.fr.gify.ui.search

import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import netgem.fr.gify.network.ApiService
import netgem.fr.gify.network.GifRepository
import netgem.fr.gify.network.data.GifModel

class GifDataSourceFactory (val repository: GifRepository, val key: String, private val disposable: CompositeDisposable, private val state: StateLiveData): DataSource.Factory<Int, GifModel>() {
    var gifDataSource: GifDataSource? = null

    override fun create(): DataSource<Int, GifModel> {
        val dataSource = GifDataSource(repository, key, disposable, state)
        gifDataSource = dataSource
        return dataSource
    }
}