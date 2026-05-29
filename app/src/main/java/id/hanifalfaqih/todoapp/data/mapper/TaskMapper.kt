package id.hanifalfaqih.todoapp.data.mapper

import id.hanifalfaqih.todoapp.data.remote.dto.response.TaskDataResponse
import id.hanifalfaqih.todoapp.domain.model.Task
import id.hanifalfaqih.todoapp.domain.model.TaskPriority

object TaskMapper {

    fun toDomain(
        response: TaskDataResponse
    ): Task {
        return Task(
            id = response.id,
            title = response.title,
            description = response.body,
            priority = mapPriority(response.priority),
            isCompleted = response.isCompleted ?: false,
            weight = response.weight,
            urgent = response.urgent
        )
    }

    private fun mapPriority(
        priority: String
    ): TaskPriority {
        return when (priority.uppercase()) {
            "HIGH" -> TaskPriority.HIGH
            "MEDIUM" -> TaskPriority.MEDIUM
            else -> TaskPriority.LOW
        }
    }
}