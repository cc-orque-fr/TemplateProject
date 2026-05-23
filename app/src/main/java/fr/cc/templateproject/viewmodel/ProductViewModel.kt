package fr.cc.templateproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.cc.templateproject.model.StoreItem
import fr.cc.templateproject.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class StoreItemsUiState() {
    object Loading : StoreItemsUiState()
    class SuccessUiState(val stores: List<StoreItem>) : StoreItemsUiState()
    class ErrorUiState(val throwable: Throwable) : StoreItemsUiState()
}

class StoreItemsViewModel(val repository: Repository) : ViewModel() {
    private val _uiState = MutableStateFlow<StoreItemsUiState>(StoreItemsUiState.Loading)
    val uiState: StateFlow<StoreItemsUiState> = _uiState

    init {
        refreshProducts()
    }


    fun refreshProducts() {
        viewModelScope.launch {
            runCatching {
                _uiState.value = StoreItemsUiState.SuccessUiState(repository.getProducts())
            }.onFailure {
                _uiState.value = StoreItemsUiState.ErrorUiState(it)
            }

        }
    }

}