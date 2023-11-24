package app.halfmouth.android.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.halfmouth.android.data.api.ApiService
import app.halfmouth.android.data.remote.ThingSpeakResponse
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

    private val results = "2"
    var response = mutableStateOf(
        ThingSpeakResponse(
            channel = null,
            feeds = emptyList()
        )
    )

    init {
        loadFirstShimmer()
    }

    private fun loadFirstShimmer() {
        viewModelScope.launch {
            response.value = service.getThingSpeakValues(results)
            _enableSwipeRefresh.value = true
            delay(5000L)
            _enableSwipeRefresh.value = false
        }
    }

    fun loadStuff() {
        viewModelScope.launch {
            response.value = service.getThingSpeakValues("2")
            _isLoading.value = true
            delay(5000L)
            _isLoading.value = false
        }
    }
}
