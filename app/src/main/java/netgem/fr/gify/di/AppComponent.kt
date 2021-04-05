package netgem.fr.gify.di

import dagger.Component
import netgem.fr.gify.ui.random.RandomGifFragment
import netgem.fr.gify.ui.search.SearchGifListFragment

@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(randomGifFragment: RandomGifFragment)
    fun inject(searchGifListFragment: SearchGifListFragment)
}