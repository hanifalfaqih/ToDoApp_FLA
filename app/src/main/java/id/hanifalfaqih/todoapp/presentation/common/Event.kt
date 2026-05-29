package id.hanifalfaqih.todoapp.presentation.common

sealed class Event {

    data class ShowMessage(
        val message: String
    ) : Event()

    data object NavigateToHome : Event()

    data object NavigateToLogin : Event()

    data object NavigateBack : Event()

}