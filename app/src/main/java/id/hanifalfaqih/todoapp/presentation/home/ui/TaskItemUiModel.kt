package id.hanifalfaqih.todoapp.presentation.home.ui

data class TaskItemUiModel (
    val id: Int,
    val title: String,
    val description: String,
    val priority: String,
    val isUrgent: Boolean
)
