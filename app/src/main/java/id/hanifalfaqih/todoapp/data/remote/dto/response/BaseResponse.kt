package id.hanifalfaqih.todoapp.data.remote.dto.response

data class BaseResponse<T>(
    val status: Int,
    val response: T
)
