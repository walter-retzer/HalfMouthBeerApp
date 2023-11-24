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

    private val _enableSwipeRefresh = MutableStateFlow(false)
    val enableSwipeRefresh = _enableSwipeRefresh.asStateFlow()

    init {
        loadFirstShimmer()
    }

    fun request() {
        viewModelScope.launch {
            service.getThingSpeakValues("2")
        }
    }

    private fun loadFirstShimmer() {
        viewModelScope.launch {
            _enableSwipeRefresh.value = true
            delay(5000L)
            _enableSwipeRefresh.value = false
        }
    }

    fun loadStuff() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(5000L)
            _isLoading.value = false
        }
    }
}
