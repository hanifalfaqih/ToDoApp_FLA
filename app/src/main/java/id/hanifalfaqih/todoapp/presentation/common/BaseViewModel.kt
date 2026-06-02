package id.hanifalfaqih.todoapp.presentation.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel : ViewModel() {

    private val _event =
        MutableSharedFlow<Event>()

    val event =
        _event.asSharedFlow()

    protected suspend fun sendEvent(
        event: Event
    ) {
        _event.emit(event)
    }
}