package id.hanifalfaqih.todoapp.presentation.auth.register

import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.todoapp.domain.usecase.RegisterUseCase
import id.hanifalfaqih.todoapp.presentation.common.BaseViewModel
import id.hanifalfaqih.todoapp.presentation.common.Event
import id.hanifalfaqih.todoapp.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : BaseViewModel() {

    private val _registerState =
        MutableStateFlow<UiState<String>>(
            UiState.Idle
        )

    val registerState: StateFlow<UiState<String>> =
        _registerState.asStateFlow()

    fun register(
        name: String,
        username: String,
        password: String
    ) {

        viewModelScope.launch {

            _registerState.value =
                UiState.Loading

            try {

                val message = registerUseCase(
                    name = name,
                    username = username,
                    password = password
                )

                _registerState.value =
                    UiState.Success(message)

                sendEvent(
                    Event.ShowMessage(message)
                )

                sendEvent(
                    Event.NavigateToLogin
                )

            } catch (e: Exception) {

                _registerState.value =
                    UiState.Error(
                        e.message ?: "Registration failed"
                    )

                sendEvent(
                    Event.ShowMessage(
                        e.message ?: "Registration failed"
                    )
                )
            }
        }
    }
}