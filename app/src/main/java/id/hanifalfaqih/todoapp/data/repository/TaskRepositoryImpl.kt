package id.hanifalfaqih.todoapp.data.repository

import id.hanifalfaqih.todoapp.data.mapper.TaskMapper
import id.hanifalfaqih.todoapp.data.remote.api.TaskApi
import id.hanifalfaqih.todoapp.data.remote.dto.request.CreateTaskRequest
import id.hanifalfaqih.todoapp.data.remote.dto.request.UpdateTaskRequest
import id.hanifalfaqih.todoapp.domain.model.Task

class TaskRepositoryImpl(
    private val taskApi: TaskApi
) : TaskRepository {
    override suspend fun getTasks(): List<Task> {
        val response = taskApi.getTasks()

        if (response.isSuccessful) {

            val tasks = response.body()
                ?.response
                ?: emptyList()

            return tasks.map {
                TaskMapper.toDomain(it)
            }
        }

        throw Exception("Failed to get tasks")
    }

    override suspend fun getTaskById(
        id: Int
    ): Task {

        val response = taskApi.getTaskById(id)

        if (response.isSuccessful) {

            val task = response.body()
                ?.response
                ?: throw Exception("Task not found")

            return TaskMapper.toDomain(task)
        }

        throw Exception("Failed to get task detail")
    }


    override suspend fun createTask(
        title: String,
        body: String,
        priority: String
    ): Task {

        val request = CreateTaskRequest(
            title = title,
            body = body,
            priority = priority
        )

        val response = taskApi.createTask(request)

        if (response.isSuccessful) {

            val task = response.body()
                ?.response
                ?: throw Exception("Task creation failed")

            return TaskMapper.toDomain(task)
        }

        throw Exception("Failed to create task")
    }

    override suspend fun updateTask(
        id: Int,
        title: String,
        body: String,
        priority: String
    ): Task {

        val request = UpdateTaskRequest(
            title = title,
            body = body,
            priority = priority
        )

        val response = taskApi.updateTask(
            id = id,
            request = request
        )

        if (response.isSuccessful) {

            val task = response.body()
                ?.response
                ?: throw Exception("Task update failed")

            return TaskMapper.toDomain(task)
        }

        throw Exception("Failed to update task")
    }

    override suspend fun deleteTask(
        id: Int
    ): String {

        val response = taskApi.deleteTask(id)

        if (response.isSuccessful) {

            return response.body()
                ?.response
                ?: "Task deleted"
        }

        throw Exception("Failed to delete task")
    }

    override suspend fun searchTask(
        keyword: String
    ): List<Task> {

        val response = taskApi.searchTask(keyword)

        if (response.isSuccessful) {

            val tasks = response.body()
                ?.response
                ?: emptyList()

            return tasks.map {
                TaskMapper.toDomain(it)
            }
        }

        throw Exception("Failed to search task")
    }
}