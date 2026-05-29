package id.hanifalfaqih.todoapp.data.remote.api

import id.hanifalfaqih.todoapp.data.remote.dto.request.LoginRequest
import id.hanifalfaqih.todoapp.data.remote.dto.request.RegisterRequest
import id.hanifalfaqih.todoapp.data.remote.dto.response.BaseResponse
import id.hanifalfaqih.todoapp.data.remote.dto.response.LoginDataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<BaseResponse<String>>

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<BaseResponse<LoginDataResponse>>

}