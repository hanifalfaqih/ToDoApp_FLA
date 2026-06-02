package id.hanifalfaqih.todoapp.data.repository

import id.hanifalfaqih.todoapp.domain.model.Task

interface TaskRepository {
    suspend fun getTasks(): List<Task>

    suspend fun getTaskById(
        id: Int
    ): Task

    suspend fun createTask(
        title: String,
        body: String,
        priority: String
    ): Task

    suspend fun updateTask(
        id: Int,
        title: String,
        body: String,
        priority: String
    ): Task

    suspend fun deleteTask(
        id: Int
    ): String

    suspend fun searchTask(
        keyword: String
    ): List<Task>
}