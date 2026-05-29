package id.hanifalfaqih.todoapp.domain.usecase

import id.hanifalfaqih.todoapp.data.repository.TaskRepository
import id.hanifalfaqih.todoapp.domain.model.Task

class SearchTaskUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(
        keyword: String
    ): List<Task> {

        return taskRepository.searchTask(keyword)
    }
}