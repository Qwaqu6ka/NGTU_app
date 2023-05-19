package com.example.foundation.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foundation.models.ErrorResult
import com.example.foundation.models.Result
import com.example.foundation.models.SuccessResult
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

typealias LiveResult<T> = LiveData<Result<T>>
typealias MutableLiveResult<T> = MutableLiveData<Result<T>>

open class BaseViewModel : ViewModel() {

    private val coroutineContext = SupervisorJob() + Dispatchers.Main.immediate
    protected val viewModelScope = CoroutineScope(coroutineContext)

    override fun onCleared() {
        super.onCleared()
        clearViewModelScope()
    }
    /**
     * Override this method in child classes if you want to listen for results
     * from other screens
     */
    open fun onResult(result: Any) {}

    /**
     * Override this method if you want to control go-back behaviour.
     * Return `true` if you want to abort closing this screen
     */
    open fun onBackPressed(): Boolean {
        clearViewModelScope()
        return false
    }

    fun <T> into(liveResult: MutableLiveResult<T>, block: suspend () -> T) {
        viewModelScope.launch {
            try {
                liveResult.postValue(SuccessResult(block()))
            } catch (e: Exception) {
                if (e !is CancellationException) liveResult.postValue(ErrorResult(e))
            }
        }
    }

    private fun clearViewModelScope() {
        viewModelScope.cancel()
    }
}