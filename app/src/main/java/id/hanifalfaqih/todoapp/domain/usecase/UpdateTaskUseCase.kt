package id.hanifalfaqih.todoapp.domain.usecase

import id.hanifalfaqih.todoapp.data.repository.TaskRepository
import id.hanifalfaqih.todoapp.domain.model.Task

class UpdateTaskUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(
        id: Int,
        title: String,
        body: String,
        priority: String
    ): Task {

        return taskRepository.updateTask(
            id = id,
            title = title,
            body = body,
            priority = priority
        )
    }
}