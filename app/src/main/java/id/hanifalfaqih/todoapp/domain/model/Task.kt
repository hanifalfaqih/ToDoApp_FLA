package id.hanifalfaqih.todoapp.domain.model


data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: TaskPriority,
    val isCompleted: Boolean,
    val weight: Int,
    val urgent: Boolean,

) {

}
