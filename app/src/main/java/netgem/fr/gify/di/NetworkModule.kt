package netgem.fr.gify.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import netgem.fr.gify.utils.BASE_URL
import netgem.fr.gify.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [ViewModelModule::class])
object NetworkModule {

    @Provides
    fun profiveRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun proviteApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}