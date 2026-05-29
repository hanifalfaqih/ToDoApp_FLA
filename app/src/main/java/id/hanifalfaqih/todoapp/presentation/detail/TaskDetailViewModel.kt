package id.hanifalfaqih.todoapp.presentation.detail

import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.todoapp.domain.model.Task
import id.hanifalfaqih.todoapp.domain.usecase.GetTaskDetailUseCase
import id.hanifalfaqih.todoapp.presentation.common.BaseViewModel
import id.hanifalfaqih.todoapp.presentation.common.Event
import id.hanifalfaqih.todoapp.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    private val getTaskDetailUseCase: GetTaskDetailUseCase
) : BaseViewModel() {

    private val _taskDetailState =
        MutableStateFlow<UiState<Task>>(
            UiState.Idle
        )

    val taskDetailState: StateFlow<UiState<Task>> =
        _taskDetailState.asStateFlow()

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
}