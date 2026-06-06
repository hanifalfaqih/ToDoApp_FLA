package id.hanifalfaqih.todoapp.domain.model

import id.hanifalfaqih.todoapp.presentation.home.ui.TaskItemUiModel

object TaskUiMapper {

    fun toUiModel(
        task: Task
    ): TaskItemUiModel {

        return TaskItemUiModel(
            id = task.id,
            title = task.title,
            description = task.description,
            priority = task.priority.name,
            state = task.getCurrentState(),
            isUrgent = task.urgent
        )
    }

    fun toUiModels(
        tasks: List<Task>
    ): List<TaskItemUiModel> {

        return tasks.map {
            toUiModel(it)
        }
    }
}