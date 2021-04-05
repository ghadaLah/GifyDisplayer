package netgem.fr.gify.ui.random

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import netgem.fr.gify.network.data.GifModel
import netgem.fr.gify.network.GifRepository
import javax.inject.Inject

class RandomGifViewModel @Inject constructor(val repository: GifRepository): ViewModel() {

    var gifObject: LiveData<GifModel> = repository.gifObject
    var gifError: LiveData<String> = repository.error

    fun getGifObject() {
        repository.getGifObject()
    }
}