package netgem.fr.gify.ui.search

import androidx.lifecycle.MutableLiveData

sealed class StateViewModel {
    object Loading: StateViewModel()
    object Success: StateViewModel()
    object Error: StateViewModel()
}
class StateLiveData(state: StateViewModel = StateViewModel.Loading): MutableLiveData<StateViewModel>() {
    init {
        value = state
    }
}