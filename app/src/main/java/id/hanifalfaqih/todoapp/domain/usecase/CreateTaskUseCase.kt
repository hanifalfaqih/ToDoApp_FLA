package id.hanifalfaqih.todoapp.domain.usecase

import id.hanifalfaqih.todoapp.data.repository.TaskRepository
import id.hanifalfaqih.todoapp.domain.model.Task

class CreateTaskUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(
        title: String,
        body: String,
        priority: String
    ): Task {

        return taskRepository.createTask(
            title = title,
            body = body,
            priority = priority
        )
    }
}