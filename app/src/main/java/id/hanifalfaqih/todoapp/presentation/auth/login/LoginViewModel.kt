package id.hanifalfaqih.todoapp.presentation.auth.login

import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.todoapp.domain.usecase.LoginUseCase
import id.hanifalfaqih.todoapp.presentation.common.BaseViewModel
import id.hanifalfaqih.todoapp.presentation.common.Event
import id.hanifalfaqih.todoapp.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): BaseViewModel() {

    private val _loginState = MutableStateFlow<UiState<Unit>>(UiState.Idle)

    val loginState: StateFlow<UiState<Unit>> =
        _loginState.asStateFlow()

    fun login(
        username: String,
        password: String
    ) {

        viewModelScope.launch {

            _loginState.value = UiState.Loading

            try {

                loginUseCase(
                    username = username,
                    password = password
                )

                _loginState.value =
                    UiState.Success(Unit)

                sendEvent(
                    Event.NavigateToHome
                )

            } catch (e: Exception) {

                _loginState.value =
                    UiState.Error(
                        e.message ?: "Login failed"
                    )

                sendEvent(
                    Event.ShowMessage(
                        e.message ?: "Login failed"
                    )
                )
            }
        }
    }

}