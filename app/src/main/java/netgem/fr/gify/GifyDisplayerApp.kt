package netgem.fr.gify

import android.app.Application
import netgem.fr.gify.di.AppComponent
import netgem.fr.gify.di.DaggerAppComponent
import netgem.fr.gify.di.NetworkModule

class GifyDisplayerApp: Application() {
   companion object {
       lateinit var appComponent: AppComponent
   }
    init {
        appComponent = DaggerAppComponent
            .builder()
            .networkModule(NetworkModule)
            .build()
    }
}