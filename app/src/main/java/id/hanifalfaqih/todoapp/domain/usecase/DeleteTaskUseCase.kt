package id.hanifalfaqih.todoapp.domain.usecase

import id.hanifalfaqih.todoapp.data.repository.TaskRepository

class DeleteTaskUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(
        id: Int
    ): String {

        return taskRepository.deleteTask(id)
    }
}