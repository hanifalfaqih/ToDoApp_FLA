package id.hanifalfaqih.todoapp.data.repository

import id.hanifalfaqih.todoapp.data.local.SessionPreference
import id.hanifalfaqih.todoapp.data.remote.api.AuthApi
import id.hanifalfaqih.todoapp.data.remote.dto.request.LoginRequest
import id.hanifalfaqih.todoapp.data.remote.dto.request.RegisterRequest

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

            return token
        }

        throw Exception("Login failed")
    }
}