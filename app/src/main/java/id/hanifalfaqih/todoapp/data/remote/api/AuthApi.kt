package id.hanifalfaqih.todoapp.data.remote.api

import id.hanifalfaqih.todoapp.data.remote.dto.request.ChangePasswordRequest
import id.hanifalfaqih.todoapp.data.remote.dto.request.LoginRequest
import id.hanifalfaqih.todoapp.data.remote.dto.request.RegisterRequest
import id.hanifalfaqih.todoapp.data.remote.dto.request.UpdateProfileRequest
import id.hanifalfaqih.todoapp.data.remote.dto.response.BaseResponse
import id.hanifalfaqih.todoapp.data.remote.dto.response.LoginDataResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<BaseResponse<String>>

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<BaseResponse<LoginDataResponse>>

    @PUT("auth/profile")
    suspend fun updateProfile(
        @Body request: UpdateProfileRequest
    ): Response<BaseResponse<String>>

    @Multipart
    @POST("auth/profile/photo")
    suspend fun updateProfilePhoto(
        @Part photo: MultipartBody.Part
    ): Response<BaseResponse<String>>

    @DELETE("auth/profile/photo")
    suspend fun deleteProfilePhoto(): Response<BaseResponse<String>>

    @PUT("auth/change-password")
    suspend fun changePassword(
        @Body request: ChangePasswordRequest
    ): Response<BaseResponse<String>>

    @DELETE("auth/account")
    suspend fun deleteAccount(): Response<BaseResponse<String>>

}