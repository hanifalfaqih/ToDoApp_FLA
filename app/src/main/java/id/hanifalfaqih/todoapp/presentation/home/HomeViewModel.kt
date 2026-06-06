package id.hanifalfaqih.todoapp.presentation.home

import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.todoapp.domain.model.Task
import id.hanifalfaqih.todoapp.domain.usecase.GetTaskUseCase
import id.hanifalfaqih.todoapp.domain.usecase.SearchTaskUseCase
import id.hanifalfaqih.todoapp.presentation.common.BaseViewModel
import id.hanifalfaqih.todoapp.presentation.common.Event
import id.hanifalfaqih.todoapp.presentation.common.UiState
import id.hanifalfaqih.todoapp.domain.model.TaskPriority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTaskUseCase: GetTaskUseCase,
    private val searchTaskUseCase: SearchTaskUseCase
): BaseViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    private val _priorityFilter = MutableStateFlow<String>("All")
    private val _searchQuery = MutableStateFlow<String>("")

    private val _taskState = MutableStateFlow<UiState<List<Task>>>(UiState.Idle)
    val taskState: StateFlow<UiState<List<Task>>> = _taskState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(_tasks, _priorityFilter, _searchQuery) { tasks, priority, query ->
                var filtered = tasks
                if (priority != "All") {
                    filtered = filtered.filter { it.priority.name.equals(priority, ignoreCase = true) }
                }
                if (query.isNotBlank()) {
                    filtered = filtered.filter { it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true) }
                }
                filtered
            }.collect {
                _taskState.value = UiState.Success(it)
            }
        }
    }

    fun loadTasks() {
        viewModelScope.launch {
            _taskState.value = UiState.Loading
            try {
                val tasks = getTaskUseCase()
                _tasks.value = tasks
            } catch (e: Exception) {
                _taskState.value = UiState.Error(e.message ?: "Failed to load tasks")
            }
        }
    }

    fun filterByPriority(priority: String) {
        _priorityFilter.value = priority
    }

    fun searchTask(keyword: String) {
        _searchQuery.value = keyword
    }
}