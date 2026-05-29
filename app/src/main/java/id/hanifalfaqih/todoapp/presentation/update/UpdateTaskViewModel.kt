package id.hanifalfaqih.todoapp.presentation.update

import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.todoapp.domain.model.Task
import id.hanifalfaqih.todoapp.domain.usecase.UpdateTaskUseCase
import id.hanifalfaqih.todoapp.presentation.common.BaseViewModel
import id.hanifalfaqih.todoapp.presentation.common.Event
import id.hanifalfaqih.todoapp.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UpdateTaskViewModel(
    private val updateTaskUseCase: UpdateTaskUseCase
) : BaseViewModel() {

    private val _updateTaskState =
        MutableStateFlow<UiState<Task>>(
            UiState.Idle
        )

    val updateTaskState: StateFlow<UiState<Task>> =
        _updateTaskState.asStateFlow()

    fun updateTask(
        id: Int,
        title: String,
        body: String,
        priority: String
    ) {

        if (
            title.isBlank() ||
            body.isBlank()
        ) {

            viewModelScope.launch {
                sendEvent(
                    Event.ShowMessage(
                        "Please fill all fields"
                    )
                )
            }

            return
        }

        viewModelScope.launch {

            _updateTaskState.value =
                UiState.Loading

            try {

                val task = updateTaskUseCase(
                    id = id,
                    title = title,
                    body = body,
                    priority = priority
                )

                _updateTaskState.value =
                    UiState.Success(task)

                sendEvent(
                    Event.ShowMessage(
                        "Task updated successfully"
                    )
                )

                sendEvent(
                    Event.NavigateBack
                )

            } catch (e: Exception) {

                _updateTaskState.value =
                    UiState.Error(
                        e.message ?: "Failed to update task"
                    )

                sendEvent(
                    Event.ShowMessage(
                        e.message ?: "Failed to update task"
                    )
                )
            }
        }
    }
}