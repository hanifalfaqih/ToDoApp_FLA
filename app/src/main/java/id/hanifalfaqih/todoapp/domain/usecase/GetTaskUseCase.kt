package id.hanifalfaqih.todoapp.domain.usecase

import id.hanifalfaqih.todoapp.data.repository.TaskRepository
import id.hanifalfaqih.todoapp.domain.model.Task

class GetTaskUseCase (
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(): List<Task> {

        return taskRepository.getTasks()
    }
}