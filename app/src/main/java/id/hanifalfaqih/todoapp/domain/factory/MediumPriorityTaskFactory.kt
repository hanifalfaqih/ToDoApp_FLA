package id.hanifalfaqih.todoapp.domain.factory

import id.hanifalfaqih.todoapp.domain.model.Task
import id.hanifalfaqih.todoapp.domain.model.TaskPriority

class MediumPriorityTaskFactory : TaskFactory() {

    override fun createTask(
        id: Int,
        title: String,
        description: String
    ): Task {

        return Task(
            id = id,
            title = title,
            description = description,
            priority = TaskPriority.MEDIUM,
            isCompleted = false,
            weight = 2,
            urgent = false
        )
    }
}