package com.aubay.pokemon.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aubay.pokemon.core.extensions.CoroutineContextProvider
import com.aubay.pokemon.core.extensions.launch
import com.aubay.pokemon.core.extensions.logDebug
import com.aubay.pokemon.core.repository.Connectivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel<T : Any, E> : ViewModel(), KoinComponent {

    protected val coroutineContext: CoroutineContextProvider by inject()
    private val connectivity: Connectivity by inject()
    protected val ioScope = CoroutineScope(Dispatchers.Default)

    protected val _viewState = MutableLiveData<ViewState<T>>()
    val viewState: LiveData<ViewState<T>>
        get() = _viewState

    protected val _viewEffects = MutableLiveData<E>()
    val viewEffects: LiveData<E>
        get() = _viewEffects

    protected fun executeUseCase(action: suspend () -> Unit, noInternetAction: () -> Unit) {
        log("onExecute $action")
        _viewState.value = Loading()
        if (connectivity.hasNetworkAccess()) {
            launch { action() }
        } else {
            log("no intermet")
            noInternetAction()
        }
    }

    protected fun executeUseCase(action: suspend () -> Unit) {
        log("onExecute $action")

        _viewState.value = Loading()
        launch { action() }
    }

    fun log(message: String){
        logDebug("${this::class.java.canonicalName}", message)
    }
}