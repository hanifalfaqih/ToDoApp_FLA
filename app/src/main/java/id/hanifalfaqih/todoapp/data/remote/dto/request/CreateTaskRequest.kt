package id.hanifalfaqih.todoapp.data.remote.dto.request

data class CreateTaskRequest(
    val title: String,
    val body: String,
    val priority: String
)
