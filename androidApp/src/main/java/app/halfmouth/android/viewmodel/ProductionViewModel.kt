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


class ProductionViewModel : ViewModel() {

    private val service = ApiService.create()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _enableSwipeRefresh = MutableStateFlow(false)
    val enableSwipeRefresh = _enableSwipeRefresh.asStateFlow()

    private val _enableLockScreen = MutableStateFlow(false)
    var enableLockScreen = _enableSwipeRefresh.asStateFlow()

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
            _enableLockScreen.value = true
            delay(5000L)
            _enableSwipeRefresh.value = false
            _enableLockScreen.value = false
        }
    }

    fun loadStuff() {
        viewModelScope.launch {
            response.value = service.getThingSpeakValues(results)
            _isLoading.value = true
            _enableLockScreen.value = true
            delay(5000L)
            _isLoading.value = false
            _enableLockScreen.value = false
        }
    }
}
