package id.hanifalfaqih.todoapp.data.remote.dto.request

data class UpdateTaskRequest(
    val title: String,
    val body: String,
    val priority: String
)
