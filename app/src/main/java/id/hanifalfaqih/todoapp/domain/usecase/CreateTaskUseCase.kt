package id.hanifalfaqih.todoapp.domain.usecase

import id.hanifalfaqih.todoapp.data.repository.TaskRepository
import id.hanifalfaqih.todoapp.domain.factory.TaskFactory
import id.hanifalfaqih.todoapp.domain.factory.TaskFactoryProvider
import id.hanifalfaqih.todoapp.domain.model.Task

class CreateTaskUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(
        title: String,
        body: String,
        priority: String
    ): Task {

        val factory = TaskFactoryProvider.getFactory(priority)

        val localTask = factory.createTask(
            id = 0,
            title = title,
            description = body
        )

        return taskRepository.createTask(
            title = localTask.title,
            body = localTask.description,
            priority = localTask.priority.name
        )
    }
}