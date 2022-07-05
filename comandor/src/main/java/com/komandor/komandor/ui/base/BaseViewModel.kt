package com.komandor.komandor.ui.base

import androidx.lifecycle.ViewModel
import com.atom.ui.viewmodel.BaseViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber

abstract class BaseViewModel constructor(viewState: BaseViewState): ViewModel() {
    val coroutineExceptionHanlder = CoroutineExceptionHandler { _, throwable ->
        updateStateExeption(throwable)
    }
    suspend fun emitException(e:Throwable){
        _state.emit(BaseViewState(
            isLoading = false,
            isComplete = true,
            exception = e
        ))
    }


    suspend fun emitProgress(){
        _state.emit(BaseViewState(
            isLoading = true,
            isComplete = false,
        ))
    }

    suspend fun emitComplete(data:Any?){
        _state.emit(BaseViewState(
            isLoading = false,
            isComplete = true,
            payload = data
        ))
    }

    fun updateStateExeption(throwable:Throwable?){
        Timber.d("updateStateExeption throwable = ${throwable}")

        _state.update {
            it.copy(
                isLoading = false,
                exception = throwable?:Throwable("Unkoun Error")
            )
        }
    }

    protected val _state: MutableStateFlow<BaseViewState> = MutableStateFlow(viewState)
    val state: StateFlow<BaseViewState>
        get() = _state
}