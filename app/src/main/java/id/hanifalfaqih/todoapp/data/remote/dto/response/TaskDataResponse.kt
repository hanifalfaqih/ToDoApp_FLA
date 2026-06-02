package id.hanifalfaqih.todoapp.data.remote.dto.response

data class TaskDataResponse(
    val id: Int,
    val title: String,
    val body: String,
    val priority: String,
    val isCompleted: Boolean?,
    val weight: Int,
    val urgent: Boolean
)
