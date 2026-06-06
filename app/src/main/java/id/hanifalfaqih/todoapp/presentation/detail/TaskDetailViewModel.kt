package id.hanifalfaqih.todoapp.presentation.detail

import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.todoapp.domain.model.Task
import id.hanifalfaqih.todoapp.domain.usecase.DeleteTaskUseCase
import id.hanifalfaqih.todoapp.domain.usecase.GetTaskDetailUseCase
import id.hanifalfaqih.todoapp.presentation.common.BaseViewModel
import id.hanifalfaqih.todoapp.presentation.common.Event
import id.hanifalfaqih.todoapp.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    private val getTaskDetailUseCase: GetTaskDetailUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : BaseViewModel() {

    private val _taskDetailState =
        MutableStateFlow<UiState<Task>>(
            UiState.Idle
        )

    val taskDetailState: StateFlow<UiState<Task>> =
        _taskDetailState.asStateFlow()

    private val _deleteTaskState =
        MutableStateFlow<UiState<String>>(
            UiState.Idle
        )

    val deleteTaskState: StateFlow<UiState<String>> =
        _deleteTaskState.asStateFlow()

    fun loadTaskDetail(
        taskId: Int
    ) {

        viewModelScope.launch {

            _taskDetailState.value =
                UiState.Loading

            try {

                val task =
                    getTaskDetailUseCase(taskId)

                _taskDetailState.value =
                    UiState.Success(task)

            } catch (e: Exception) {

                _taskDetailState.value =
                    UiState.Error(
                        e.message ?: "Failed to load task detail"
                    )

                sendEvent(
                    Event.ShowMessage(
                        e.message ?: "Failed to load task detail"
                    )
                )
            }
        }
    }

    fun deleteTask(
        taskId: Int
    ) {

        viewModelScope.launch {

            _deleteTaskState.value =
                UiState.Loading

            try {

                val message =
                    deleteTaskUseCase(taskId)

                _deleteTaskState.value =
                    UiState.Success(message)

                sendEvent(
                    Event.ShowMessage(message)
                )

                sendEvent(
                    Event.NavigateBack
                )

            } catch (e: Exception) {

                _deleteTaskState.value =
                    UiState.Error(
                        e.message ?: "Failed to delete task"
                    )

                sendEvent(
                    Event.ShowMessage(
                        e.message ?: "Failed to delete task"
                    )
                )
            }
        }
    }
}