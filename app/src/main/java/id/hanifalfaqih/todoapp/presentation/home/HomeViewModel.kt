package id.hanifalfaqih.todoapp.presentation.home

import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.todoapp.domain.model.Task
import id.hanifalfaqih.todoapp.domain.usecase.GetTaskUseCase
import id.hanifalfaqih.todoapp.domain.usecase.SearchTaskUseCase
import id.hanifalfaqih.todoapp.presentation.common.BaseViewModel
import id.hanifalfaqih.todoapp.presentation.common.Event
import id.hanifalfaqih.todoapp.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTaskUseCase: GetTaskUseCase,
    private val searchTaskUseCase: SearchTaskUseCase
): BaseViewModel() {

    private val _taskState =
        MutableStateFlow<UiState<List<Task>>>(
            UiState.Idle
        )

    val taskState: StateFlow<UiState<List<Task>>> =
        _taskState.asStateFlow()

    fun loadTasks() {

        viewModelScope.launch {

            _taskState.value =
                UiState.Loading

            try {

                val tasks =
                    getTaskUseCase()

                _taskState.value =
                    UiState.Success(tasks)

            } catch (e: Exception) {

                _taskState.value =
                    UiState.Error(
                        e.message ?: "Failed to load tasks"
                    )

                sendEvent(
                    Event.ShowMessage(
                        e.message ?: "Failed to load tasks"
                    )
                )
            }
        }
    }

    fun searchTask(
        keyword: String
    ) {

        viewModelScope.launch {

            _taskState.value =
                UiState.Loading

            try {

                val tasks =
                    searchTaskUseCase(keyword)

                _taskState.value =
                    UiState.Success(tasks)

            } catch (e: Exception) {

                _taskState.value =
                    UiState.Error(
                        e.message ?: "Failed to search tasks"
                    )

                sendEvent(
                    Event.ShowMessage(
                        e.message ?: "Failed to search tasks"
                    )
                )
            }
        }
    }

}