package id.hanifalfaqih.todoapp.data.model

enum class Priority {
    HIGH, MEDIUM, LOW
}

data class Note(
    val title: String,
    val content: String,
    val priority: Priority
)