package id.hanifalfaqih.todoapp.data.remote.api

import id.hanifalfaqih.todoapp.data.remote.dto.request.CreateTaskRequest
import id.hanifalfaqih.todoapp.data.remote.dto.request.UpdateTaskRequest
import id.hanifalfaqih.todoapp.data.remote.dto.response.BaseResponse
import id.hanifalfaqih.todoapp.data.remote.dto.response.TaskDataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TaskApi {

    @GET("tasks")
    suspend fun getTasks(): Response<BaseResponse<List<TaskDataResponse>>>

    @GET("tasks/id/{id}")
    suspend fun getTaskById(
        @Path("id") id: Int
    ): Response<BaseResponse<TaskDataResponse>>

    @POST("tasks")
    suspend fun createTask(
        @Body request: CreateTaskRequest
    ): Response<BaseResponse<TaskDataResponse>>

    @PUT("tasks/id/{id}")
    suspend fun updateTask(
        @Path("id") id: Int,
        @Body request: UpdateTaskRequest
    ): Response<BaseResponse<TaskDataResponse>>

    @DELETE("tasks/id/{id}")
    suspend fun deleteTask(
        @Path("id") id: Int
    ): Response<BaseResponse<String>>

    @GET("tasks/search")
    suspend fun searchTask(
        @Query("keyword") keyword: String
    ): Response<BaseResponse<List<TaskDataResponse>>>

}