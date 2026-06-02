package id.hanifalfaqih.todoapp.presentation.create

import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.todoapp.domain.model.Task
import id.hanifalfaqih.todoapp.domain.usecase.CreateTaskUseCase
import id.hanifalfaqih.todoapp.presentation.common.BaseViewModel
import id.hanifalfaqih.todoapp.presentation.common.Event
import id.hanifalfaqih.todoapp.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateTaskViewModel(
    private val createTaskUseCase: CreateTaskUseCase
) : BaseViewModel() {

    private val _createTaskState =
        MutableStateFlow<UiState<Task>>(
            UiState.Idle
        )

    val createTaskState: StateFlow<UiState<Task>> =
        _createTaskState.asStateFlow()

    fun createTask(
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

            _createTaskState.value =
                UiState.Loading

            try {

                val task = createTaskUseCase(
                    title = title,
                    body = body,
                    priority = priority
                )

                _createTaskState.value =
                    UiState.Success(task)

                sendEvent(
                    Event.ShowMessage(
                        "Task created successfully"
                    )
                )

                sendEvent(
                    Event.NavigateBack
                )

            } catch (e: Exception) {

                _createTaskState.value =
                    UiState.Error(
                        e.message ?: "Failed to create task"
                    )

                sendEvent(
                    Event.ShowMessage(
                        e.message ?: "Failed to create task"
                    )
                )
            }
        }
    }
}