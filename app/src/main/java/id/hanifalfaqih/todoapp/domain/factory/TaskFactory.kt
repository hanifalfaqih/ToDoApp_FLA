package id.hanifalfaqih.todoapp.domain.factory

import id.hanifalfaqih.todoapp.domain.model.Task

abstract class TaskFactory {
    abstract fun createTask(
        id: Int,
        title: String,
        description: String
    ): Task
}