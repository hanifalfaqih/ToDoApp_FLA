package id.hanifalfaqih.todoapp.domain.factory

import id.hanifalfaqih.todoapp.domain.model.Task
import id.hanifalfaqih.todoapp.domain.model.TaskPriority

class LowPriorityTaskFactory : TaskFactory() {

    override fun createTask(
        id: Int,
        title: String,
        description: String
    ): Task {

        return Task(
            id = id,
            title = title,
            description = description,
            priority = TaskPriority.LOW,
            isCompleted = false,
            weight = 1,
            urgent = false
        )
    }
}