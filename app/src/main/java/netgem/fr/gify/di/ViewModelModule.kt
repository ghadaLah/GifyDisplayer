package netgem.fr.gify.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import netgem.fr.gify.ui.random.RandomGifViewModel
import netgem.fr.gify.ui.search.SearchGifListViewModel
import netgem.fr.gify.utils.ViewModelFactory
import netgem.fr.gify.utils.ViewModelKey

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(RandomGifViewModel::class)
    abstract fun provideRandomGifViewModel(randomGifViewModel: RandomGifViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchGifListViewModel::class)
    abstract fun provideSearchGifListViewModel(searchGifListViewModel: SearchGifListViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}