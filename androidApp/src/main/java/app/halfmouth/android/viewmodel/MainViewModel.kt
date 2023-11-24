package app.halfmouth.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.halfmouth.android.data.api.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val service = ApiService.create()
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadStuff()
    }

    fun request() {
        viewModelScope.launch {
            service.getThingSpeakValues("2")
        }
    }



    fun loadStuff() {
        viewModelScope.launch {
            //service.getThingSpeakValues("2")
            _isLoading.value = true
            delay(30000L)
            _isLoading.value = false
        }
    }
}
