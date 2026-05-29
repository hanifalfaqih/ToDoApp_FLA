package id.hanifalfaqih.todoapp.domain.usecase

import id.hanifalfaqih.todoapp.data.repository.TaskRepository
import id.hanifalfaqih.todoapp.domain.model.Task

class GetTaskDetailUseCase (
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(
        id: Int
    ): Task {

        return taskRepository.getTaskById(id)
    }
}