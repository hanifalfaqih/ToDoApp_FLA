package id.hanifalfaqih.todoapp.data.repository

import id.hanifalfaqih.todoapp.data.local.SessionPreference
import id.hanifalfaqih.todoapp.data.remote.api.AuthApi
import id.hanifalfaqih.todoapp.data.remote.dto.request.ChangePasswordRequest
import id.hanifalfaqih.todoapp.data.remote.dto.request.LoginRequest
import id.hanifalfaqih.todoapp.data.remote.dto.request.RegisterRequest
import id.hanifalfaqih.todoapp.data.remote.dto.request.UpdateProfileRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val sessionPreference: SessionPreference
): AuthRepository {
    override suspend fun register(
        name: String,
        username: String,
        password: String
    ): String {
        val request = RegisterRequest(
            name = name,
            username = username,
            password = password
        )

        val response = authApi.register(request)

        if (response.isSuccessful) {
            sessionPreference.saveName(name)
            return response.body()?.response
                ?: throw Exception("Empty response")
        }

        throw Exception("Register failed")
    }

    override suspend fun login(username: String, password: String): String {
        val request = LoginRequest(
            username = username,
            password = password
        )

        val response = authApi.login(request)

        if (response.isSuccessful) {

            val token = response.body()
                ?.response
                ?.token
                ?: throw Exception("Token not found")

            sessionPreference.saveToken(token)
            sessionPreference.saveName(username) // Save username as name since API doesn't return it

            return token
        }

        throw Exception("Login failed")
    }

    override suspend fun updateProfile(name: String, email: String): String {
        val request = UpdateProfileRequest(name, email)
        val response = authApi.updateProfile(request)
        if (response.isSuccessful) {
            sessionPreference.saveName(name)
            return response.body()?.response ?: "Profile updated"
        }
        throw Exception("Update profile failed")
    }

    override suspend fun updateProfilePhoto(photo: File): String {
        val requestFile = photo.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("photo", photo.name, requestFile)
        val response = authApi.updateProfilePhoto(body)
        if (response.isSuccessful) {
            return response.body()?.response ?: "Photo updated"
        }
        throw Exception("Update photo failed")
    }

    override suspend fun deleteProfilePhoto(): String {
        val response = authApi.deleteProfilePhoto()
        if (response.isSuccessful) {
            return response.body()?.response ?: "Photo deleted"
        }
        throw Exception("Delete photo failed")
    }

    override suspend fun changePassword(oldPass: String, newPass: String): String {
        val request = ChangePasswordRequest(oldPass, newPass)
        val response = authApi.changePassword(request)
        if (response.isSuccessful) {
            return response.body()?.response ?: "Password changed"
        }
        throw Exception("Change password failed")
    }

    override suspend fun deleteAccount(): String {
        val response = authApi.deleteAccount()
        if (response.isSuccessful) {
            sessionPreference.clearSession()
            return response.body()?.response ?: "Account deleted"
        }
        throw Exception("Delete account failed")
    }
}