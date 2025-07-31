package com.bilyoner.bets.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bilyoner.bets.core.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> collectFlow(
        flow: Flow<Resource<T>>,
        onLoading: () -> Unit = {},
        onSuccess: (T) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            flow.collectLatest { result ->
                when (result) {
                    is Resource.Loading -> onLoading()
                    is Resource.Success -> onSuccess(result.data)
                    is Resource.Error -> onError(result.message ?: "Unknown error")
                }
            }
        }
    }
}