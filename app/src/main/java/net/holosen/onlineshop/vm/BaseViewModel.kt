package net.holosen.onlineshop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.holosen.onlineshop.model.ApiResponse
import net.holosen.onlineshop.ui.state.DataUiState

open class BaseViewModel : ViewModel() {

    protected fun <T> loadData(
        stateSetter: (DataUiState<T>) -> Unit,
        loader: suspend () -> ApiResponse<T>
    ) {
        stateSetter(DataUiState(isLoading = true))
        viewModelScope.launch {
            try {
                val response = loader()
                if (response.status != "OK") {
                    throw Exception(response.message)
                }
                stateSetter(DataUiState(data = response.data))
            } catch (e: Exception) {
                stateSetter(DataUiState(error = e.message))
            }
        }
    }
}